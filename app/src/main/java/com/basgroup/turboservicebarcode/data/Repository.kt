package com.basgroup.turboservicebarcode.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.basgroup.turboservicebarcode.data.dbmodels.ArticleSelectItem
import com.basgroup.turboservicebarcode.data.network.RetrofitFactory
import com.basgroup.turboservicebarcode.data.networkmodels.warehouseVirtuals.WarehouseVirtualsRoot
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Credentials


class Repository {

    var service = RetrofitFactory().makeRetrofitService()
    var moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    var SESSIONID=String()
    var result = MutableLiveData<String>()//TODO: Нормальный абстрактный объект обсервер, чтоб менять ток onNext, если все ок, то по изменению этой переменной должен просто возникать тост
    var warehouses = MutableLiveData<WarehouseVirtualsRoot>()
    var warehousesRes = MutableLiveData<String>()
    var foundItems=MutableLiveData<MutableList<ArticleSelectItem>?>()
    var barcodeRes=MutableLiveData<String>()
    var addedItems = MutableLiveData<MutableList<ArticleSelectItem?>>()

    fun auth(username:String, password:String) {

        val r = service.auth(Credentials.basic(username, password))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe( {
            SESSIONID = it.sessionid ?: ""
            result.postValue("success")
            Log.e("auth", SESSIONID)
        },
        { error ->
            // Логируем ошибку
            result.postValue(error.message)
            Log.e("auth", error.toString())
        })

        /*observable
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .map {
            // do computations
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            // update ui
        }*/

        /*.registerObserver(object: Observer<UserRetrofit>{
                    override fun onNext(t: UserRetrofit) {

                    }
                    override fun onSubscribe(d: Disposable) {

                    }
                    override fun onError(e: Throwable) {
                        error.postValue("Ошибка аутентификации")
                    }
                    override fun onComplete() {

                    }})*/

    }
    fun getWarehouses(){
        var r = service.getWarehouses(SESSIONID)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe( {
                warehouses.postValue(it)
                warehousesRes.postValue("success")
            },
            { error ->
                warehousesRes.postValue(error.toString())
            })

    }

    fun getItemsByBarcode(context: Context,barcode:String){
        //val filterString="(SCAN_CODE like '$barcode') or (CATALOG_CODE like '$barcode')"
        val whId = getIntSharedPref(context,"whId")
        val wVId = getIntSharedPref(context,"wVId")
        foundItems.value= null
        val rBarcode = service.getItemByBarcode(SESSIONID,p0=barcode,p1=barcode)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe( {
                Log.e("rep",it.result.response?.articles?.data!!.size.toString())
                for (i in it.result.response?.articles?.data!!){
                    Log.e("rep","for")
                    var arId=i.articleId
                    var code = i.articleCode
                    //var producerId = i.producerId
                    var barcode = i.scanCode
                    if (barcode=="") barcode=i.catalogCode
                    val name = i.articleName
                    val producer = i.Relations.producer.producerName

                    val rRest = service.getRestCurrent(i.articleId,wVId!!,SESSIONID)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .subscribe({
                            Log.e("rep","getRestCurrent")
                            val count = it.result?.response?.articleRestCurrent?.data?.rest?:0
                            val rPlace = service.getPlace(i.articleId,whId!!,SESSIONID)
                                .subscribeOn(Schedulers.io())
                                .observeOn(Schedulers.computation())
                                .subscribe({
                                    //Log.e("rep","getPlace")
                                    val place = it.result.response?.articlePlaceAndPrice?.data?.place?:"Место не указано"//TODO getString или это оставить
                                    val list = ArrayList(foundItems.value.orEmpty())
                                    Log.e("rep","$code $barcode $name $producer $count $place")
                                    list.add(ArticleSelectItem(arId,code,producer,barcode,name,count,place,0))
                                    Log.e("rep",list.toString()?:"Empty list")
                                    barcodeRes.postValue("success")
                                    foundItems.postValue(list)//мб свич с баркодрезом местами
                                },{ Log.e("rep", it.toString())
                                    barcodeRes.postValue(it.toString())})
                        },{ Log.e("rep", it.toString())
                            barcodeRes.postValue(it.toString())})
                }
            },
            {
                //warehousesRes.postValue(error.toString())
                Log.e("rep", it.toString())
                barcodeRes.postValue(it.toString())
            })

    }


    fun writeToSharedPref(context: Context,key:String,string: String){
        val shPref = context.getSharedPreferences("com.basgroup.turboservicebarcode", Context.MODE_PRIVATE)
        shPref.edit().putString(key,string).apply()
    }
    fun writeIntToSharedPref(context: Context,key:String,v: Int){
        val shPref = context.getSharedPreferences("com.basgroup.turboservicebarcode", Context.MODE_PRIVATE)
        shPref.edit().putInt(key,v).apply()
    }
    fun getStringSharedPref(context: Context,key:String):String?{
        val shPref = context.getSharedPreferences("com.basgroup.turboservicebarcode", Context.MODE_PRIVATE)
        return shPref.getString(key,"")
    }
    fun getIntSharedPref(context: Context,key:String):Int?{
        val shPref = context.getSharedPreferences("com.basgroup.turboservicebarcode", Context.MODE_PRIVATE)
        return shPref.getInt(key,Int.MAX_VALUE)
    }


}
package com.basgroup.turboservicebarcode.ui.foundItems

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.basgroup.turboservicebarcode.data.Repository
import com.basgroup.turboservicebarcode.data.dbmodels.ArticleSelectItem


class FoundItemsViewModel(private val rep: Repository, application: Application) : AndroidViewModel(application) {
    var foundItems=MutableLiveData<MutableList<ArticleSelectItem>?>()
    var observer = Observer<MutableList<ArticleSelectItem>?>{
        foundItems.postValue(it)
    }
    var listObserver = Observer<MutableList<ArticleSelectItem?>>{
        Log.e("vmfound","start")
        var list = it
        Log.e("vmfound",ar.toString())
        list?.add(ar)
        Log.e("vmfound",list.toString())
        rep.addedItems.postValue(list)
    }
    lateinit var ar:ArticleSelectItem
    fun postFoundItems(v:ArticleSelectItem){
        //ar=v
        var list = rep.addedItems.value
        if(list!=null){
        list!!.add(v)
        rep.addedItems.postValue(list)}
        else rep.addedItems.postValue(mutableListOf(v))
        //var list = rep.addedItems
        //list?.value?.add(v)

        //rep.addedItems.observeForever(listObserver)
        /*rep.addedItems.observeForever {
            var list = it
            if (list!=null){
                list.add(v)
                Log.e("vmfound",list.toString())
                rep.addedItems.postValue(list)
            }else rep.addedItems.postValue(mutableListOf(v))
        }*/
        /*rep.addedItems.observe(getApplication()) {//observeforever
            var list = it
            list?.add(v)
            Log.e("vmfound",list.toString())
            rep.addedItems.postValue(list)
        }*/
        //Log.e("vmfound",list.value.toString())
        //Log.e("vmfound",v.toString())
        //rep.addedItems.postValue(list.value)
        //rep.addedItems.value=list
        //rep.addedItems.value?.add(v)
        //rep.addedItems.value=rep.addedItems.value
        //rep.addedItems.postValue(mutableListOf(v))//рабочая, но не добавляет в лист, а заменяет его
    }
    fun getFoundItems(){
        rep.foundItems.observeForever(observer)
    }

    override fun onCleared() {
        rep.foundItems.removeObserver(observer)
        //rep.addedItems.removeObservers()
        rep.addedItems.removeObserver(listObserver)
    }

}
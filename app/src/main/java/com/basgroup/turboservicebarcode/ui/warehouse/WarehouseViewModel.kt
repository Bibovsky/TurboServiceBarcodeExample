package com.basgroup.turboservicebarcode.ui.warehouse

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.basgroup.turboservicebarcode.data.Repository
import com.basgroup.turboservicebarcode.data.networkmodels.warehouseVirtuals.WarehouseVirtuals
import com.basgroup.turboservicebarcode.data.networkmodels.warehouseVirtuals.WarehouseVirtualsRoot


class WarehouseViewModel(private val rep: Repository, application: Application) : AndroidViewModel(application){
    val warehouses = MutableLiveData<WarehouseVirtualsRoot>()
    val warehObs = Observer<WarehouseVirtualsRoot> {
        warehouses.postValue(it)
    }
    fun getWarehouses(){
        rep.getWarehouses()
        rep.warehouses.observeForever(warehObs)
    }
    fun chooseWarehouses(name:String){
        val wVId = warehouses.value?.result?.response?.warehouseVirtuals?.data?.find{ it.whvirtualName==name}?.whvirtualId
        val whId = warehouses.value?.result?.response?.warehouseVirtuals?.data?.find{ it.whvirtualName==name}?.whId
        Log.e("WHvm","$whId $wVId")
        rep.writeIntToSharedPref(getApplication(), "whId",whId?:Int.MAX_VALUE)
        rep.writeIntToSharedPref(getApplication(), "wVId",wVId?:Int.MAX_VALUE)
    }
    override fun onCleared() {
        rep.warehouses.removeObserver(warehObs)
    }

}
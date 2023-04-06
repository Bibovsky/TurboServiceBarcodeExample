package com.basgroup.turboservicebarcode.ui.scanner

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.basgroup.turboservicebarcode.data.Repository
import com.basgroup.turboservicebarcode.data.networkmodels.warehouseVirtuals.WarehouseVirtuals
import com.basgroup.turboservicebarcode.data.networkmodels.warehouseVirtuals.WarehouseVirtualsRoot


class ScannerViewModel(private val rep: Repository, application: Application) : AndroidViewModel(
    application
) {

    fun findByBarcode(barcode:String){
        rep.getItemsByBarcode(getApplication(),barcode)

    }

    override fun onCleared() {
        //rep.warehouses.removeObserver(warehObs)
    }

}
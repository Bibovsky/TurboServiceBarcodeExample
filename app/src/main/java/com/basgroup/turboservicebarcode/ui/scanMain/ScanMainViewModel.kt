package com.basgroup.turboservicebarcode.ui.scanMain

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.basgroup.turboservicebarcode.data.Repository
import com.basgroup.turboservicebarcode.data.dbmodels.ArticleSelectItem


class ScanMainViewModel(private val rep: Repository) : ViewModel() {
    var addedItems= MutableLiveData<MutableList<ArticleSelectItem?>>()

    var observer = Observer<MutableList<ArticleSelectItem?>>{
        //Log.e("vm",it.toString())
        addedItems.postValue(it)
    }
    fun loadAddedItems(){
        rep.addedItems.observeForever(observer)
    }

    /*fun getAddedItems():MutableList<ArticleSelectItem?>{
        //rep.addedItems.observeForever(observer)
        return rep.addedItems.value

    }*/
    override fun onCleared() {
        rep.addedItems.removeObserver(observer)
    }
}
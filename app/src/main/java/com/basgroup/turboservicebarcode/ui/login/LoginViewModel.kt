package com.basgroup.turboservicebarcode.ui.login

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.basgroup.turboservicebarcode.R
import com.basgroup.turboservicebarcode.data.Repository
import com.basgroup.turboservicebarcode.ui.pin.PinActivity


class LoginViewModel(private val rep: Repository, application: Application) : AndroidViewModel(application) {

    var res = MutableLiveData<String>()
    val resultObserver = Observer<String>{
        Log.e("resultVm",it)
        if (it=="success"){
            res.postValue("success")
            //rep.result.value=""
        }else {
            //rep.result.postValue("")
            res.postValue("error")
        }
    }
    fun auth(username: String?, password: String?) {
        rep.auth(username ?: "", password ?: "")

        rep.result.observeForever(resultObserver)


    }

    fun saveLogPass(username: String?, password: String?) {
        var lg = username?:""
        var pw = password?:""
        rep.writeToSharedPref(getApplication(),"login",lg)
        rep.writeToSharedPref(getApplication(),"password",pw)
    }
    override fun onCleared() {
        rep.result.postValue("")
        rep.result.removeObserver(resultObserver)
    }

}
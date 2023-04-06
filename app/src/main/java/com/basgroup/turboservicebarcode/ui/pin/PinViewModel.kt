package com.basgroup.turboservicebarcode.ui.pin

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.basgroup.turboservicebarcode.R
import com.basgroup.turboservicebarcode.data.Repository
import org.koin.dsl.koinApplication

    class PinViewModel(private val rep: Repository, application: Application) : AndroidViewModel(application) {
    //TODO: если пинкод из шейрдпреференс есть - текствью текст введите пин, если нет - создайте пин.
    val pinCode = MutableLiveData<String>().also {
        it.value = ""
    }
    //val shPref =
    //    application.getSharedPreferences("com.basgroup.turboservicebarcode", Context.MODE_PRIVATE)
    val login = rep.getStringSharedPref(getApplication(),"login") //shPref.getString("login", "")
    val pinByLogin = rep.getStringSharedPref(getApplication(),"pin$login") //shPref.getString("pin$login", "")


    val pinText = MutableLiveData<String>().also {
        it.value = ""
    }
    var result = MutableLiveData<String>()

    fun isPinExist() {

        Log.e("isPinExist", "pin:${pinByLogin.toString()} login:${login.toString()}")
        if (pinByLogin == "") {
            pinText.postValue("Создайте пин-код")
            //pinText.value = "Создайте пин-код"
            Log.e("isPinExist", pinText.value!!)
        } else {
            //pinText.value = "Введите пин-код"
            pinText.postValue("Введите пин-код")
            Log.e("isPinExist", pinText.value!!)
        }
    }


    val numPadListener = object : NumPadListener {
        override fun onNumberClicked(number: Char) {
            var newPassCode = ""
            val existingPassCode = pinCode.value ?: ""
            if (existingPassCode.length < 4) {
                newPassCode = existingPassCode + number
                pinCode.postValue(newPassCode)
                Log.e("pinviewmodel", newPassCode)
                if (newPassCode.length == 4) {
                    if (pinByLogin == "") {
                        //Log.e("onNumberClicked=4", newPassCode)
                        //shPref.edit().putString("pin$login",newPassCode).apply()
                        rep.writeToSharedPref(getApplication(),"pin$login",newPassCode)
                        result.postValue("Пин-код создан")
                    }else{
                        if (newPassCode!=pinByLogin){
                            result.postValue("Неправильный пин-код")
                            newPassCode=""
                            pinCode.postValue("")
                        }else{
                            result.postValue("Вы ввели правильный пин-код")
                        }
                    }
                }
            }

        }

        override fun onEraseClicked() {
            val droppedLast = pinCode.value?.dropLast(1) ?: ""
            pinCode.postValue(droppedLast)
            Log.e("pinviewmodel", droppedLast)
        }
    }
}

interface NumPadListener {
    fun onNumberClicked(number: Char)
    fun onEraseClicked()
}
package com.basgroup.turboservicebarcode.ui.pin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ExperimentalGetImage
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.basgroup.turboservicebarcode.R
import com.basgroup.turboservicebarcode.databinding.ActivityPinBinding
import com.basgroup.turboservicebarcode.ui.scanMain.ScanMainActivity
import com.basgroup.turboservicebarcode.ui.warehouse.WarehouseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalGetImage class PinActivity : AppCompatActivity() {
    private val vm by viewModel<PinViewModel>()


    private val binding : ActivityPinBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_pin)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        vm.isPinExist()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observers()

        binding.pinViewModel=vm
        binding.lifecycleOwner=this

    }
    private fun observers(){
        vm.pinText.observe(this, Observer {
            binding.textView.text=it
        })
        vm.result.observe(this, Observer<String>{result->
            Toast.makeText(this,result,Toast.LENGTH_SHORT).show()
            if (result.toString()=="Вы ввели правильный пин-код"||result.toString()=="Пин-код создан"){
                //ScanMainActivity.start(this)
                WarehouseActivity.start(this)
                Log.e("resobs",result)
            }

        })
    }

    companion object{
        fun start(context: Context){
            val intent = Intent(context, PinActivity::class.java)
            context.startActivity(intent)
        }
    }
}
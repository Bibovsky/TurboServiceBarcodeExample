package com.basgroup.turboservicebarcode.ui.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.basgroup.turboservicebarcode.R
import com.basgroup.turboservicebarcode.databinding.ActivityLoginBinding
import com.basgroup.turboservicebarcode.ui.pin.PinActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val vm by viewModel<LoginViewModel>()
    //private val rep by inject<Repository>(Repository::class.java)



    private val binding : ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        clearHint(binding.editTextLogin)
        clearHint(binding.editTextPassword)

        val resultObserver = Observer<String>{
            Log.e("resultAct",it)
            if (it=="success"){
                Log.e("resultActSucc",it)
                vm.res.value=""
                vm.saveLogPass(binding.editTextLogin.text.toString(),binding.editTextLogin.text.toString())//для автозаполнения
                Toast.makeText(this,R.string.login_success,Toast.LENGTH_SHORT).show()

                PinActivity.start(this)
            }else if (it=="error"){
                vm.res.value=""
                Toast.makeText(this,R.string.login_error,Toast.LENGTH_SHORT).show()
            }
        }
        vm.res.observe(this,resultObserver)





        //setupKoinFragmentFactory()

        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }*/
        /*supportFragmentManager.beginTransaction()
            .replace(R.id.container,LoginFragment::class.java,null)
            .commit()*/
        binding.loginViewModel=vm
        binding.lifecycleOwner=this

    }

    override fun onResume() {
        super.onResume()
        //vm.res.postValue("")
    }

    fun clearHint(editText: EditText){
        editText.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                editText.hint=""
            }else{
                 when (editText.id){
                     R.id.editTextLogin->editText.hint=getString(R.string.login)
                     R.id.editTextPassword->editText.hint=getString(R.string.password)
                     else -> {}
                 }
            }
        }
    }

}
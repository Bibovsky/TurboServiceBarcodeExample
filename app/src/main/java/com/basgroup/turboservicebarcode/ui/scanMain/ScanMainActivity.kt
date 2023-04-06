package com.basgroup.turboservicebarcode.ui.scanMain

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.camera.view.TransformExperimental
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.basgroup.turboservicebarcode.R
import com.basgroup.turboservicebarcode.databinding.ActivityScanMainBinding
import com.basgroup.turboservicebarcode.ui.adapters.MainAdapter
import com.basgroup.turboservicebarcode.ui.scanner.ScannerActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


@TransformExperimental class ScanMainActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_scan_toolbar, menu)
        //val searchItem = menu?.findItem(R.id.action_search)
        //val searchView = searchItem?.actionView as SearchView
        //val v = resources.getIdentifier("android:id/action_search",null,null) as SearchView
        //searchView.

        return super.onCreateOptionsMenu(menu)
    }

    private val vm by viewModel<ScanMainViewModel>()

    private val binding : ActivityScanMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_scan_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMainscan)
        supportActionBar?.title=""
        binding.btnLaunchScan.setOnClickListener { ScannerActivity.start(this) }
        vm.loadAddedItems()

        //binding.scanRv.adapter=MainAdapter(vm.getAddedItems())
        //binding.scanRv.layoutManager= LinearLayoutManager(this)
        vm.addedItems.observe(this){
            if (it != null) {

                binding.scanRv.adapter=MainAdapter(it)
                binding.bottomNavView.cardAmountText.text=
                    (binding.scanRv.adapter as Adapter).itemCount.toString()
                binding.scanRv.layoutManager=LinearLayoutManager(this)
            }
        }

    }



    companion object{
        fun start(context: Context){
            val intent = Intent(context, ScanMainActivity::class.java)
            context.startActivity(intent)
        }
    }

}

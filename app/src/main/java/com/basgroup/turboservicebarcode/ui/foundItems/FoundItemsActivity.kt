package com.basgroup.turboservicebarcode.ui.foundItems

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.basgroup.turboservicebarcode.R
import com.basgroup.turboservicebarcode.data.dbmodels.ArticleSelectItem
import com.basgroup.turboservicebarcode.databinding.ActivityFoundItemsBinding
import com.basgroup.turboservicebarcode.databinding.ActivityScanMainBinding
import com.basgroup.turboservicebarcode.ui.adapters.foundItemAdapter
import com.basgroup.turboservicebarcode.ui.pin.PinActivity
import com.basgroup.turboservicebarcode.ui.scanMain.ScanMainActivity
import com.basgroup.turboservicebarcode.ui.scanMain.ScanMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FoundItemsActivity : AppCompatActivity() {

    private val vm by viewModel<FoundItemsViewModel>()

    private val binding: ActivityFoundItemsBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_found_items)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm.getFoundItems()
        binding.textViewBarcode.text=getString(R.string.found_choose)

        binding.srcViewFoundItems.setIconifiedByDefault(false)
        binding.srcViewFoundItems.queryHint=getString(R.string.search_found_hint)

        vm.foundItems.observe(this) {
            if (it != null) {
                var s = getString(R.string.found_choose) +"\n"+ it[0].barcode
                binding.textViewBarcode.text=s
                binding.foundRv.adapter = foundItemAdapter(it,this)
                binding.foundRv.layoutManager = LinearLayoutManager(this)
            }
        }

    }
    fun itemChosen(v:ArticleSelectItem){
        vm.postFoundItems(v)
        ScanMainActivity.start(this)
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, FoundItemsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
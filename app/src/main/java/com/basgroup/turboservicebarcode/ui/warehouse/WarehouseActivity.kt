package com.basgroup.turboservicebarcode.ui.warehouse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ExperimentalGetImage
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.basgroup.turboservicebarcode.R
import com.basgroup.turboservicebarcode.databinding.ActivityWarehouseBinding
import com.basgroup.turboservicebarcode.ui.scanner.ScannerActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


@ExperimentalGetImage
class WarehouseActivity : AppCompatActivity() {
    private val vm by viewModel<WarehouseViewModel>()
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_scan_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private val binding : ActivityWarehouseBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_warehouse)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var spinnerhint = getString(R.string.spinner_warehouse)
        var list = mutableListOf<String>()
        val spnAdapter = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            list
        ) {
            /*override fun isEnabled(position: Int): Boolean {
                return position != 0
            }*/
            override fun getCount(): Int {
                return super.getCount()//-1
            }

            /*override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var tv=TextView(context)
                tv.text=getString(R.string.spinner_warehouse)
                return super.getView(position, tv, parent)
            }*/

            /*override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                /*var v = super.getDropDownView(position, convertView, parent);
                if ((v as TextView).text.toString()==getString(R.string.spinner_warehouse)){
                    v = TextView(context)
                    v.height=0
                    v.visibility=View.GONE
                    Log.e("adapter","if")
                    //v = super.getDropDownView(position, null, parent);
                    return v
                }else
                {
                    Log.e("adapter",(v as TextView).text.toString())
                    return v
                }*/
                var v=super.getDropDownView(position, convertView, parent)
                if ((v as TextView).text!=spinnerhint)
                    return v
                else{
                    val tv=CheckedTextView(context)
                    tv.height=0
                    tv.text=spinnerhint
                    return super.getDropDownView(position, tv, parent)
                    /*var v = super.getDropDownView(position, convertView, parent)
                    var context= v.context
                    val tv=CheckedTextView(context)
                    tv.height=0
                    tv.visibility=View.GONE
                    return tv*/
                    /*v.visibility=View.GONE
                    v.scaleX=0.0f
                    v.scaleY=0.0f
                    return v*/
                    /*val tv=CheckedTextView(context)
                    tv.height=0
                    tv.visibility=View.GONE
                    return tv*/
                }

            }*/
        }
        binding.spnWarehouses.adapter = spnAdapter
        binding.spnWarehouses.setSelection(binding.spnWarehouses.adapter.count)
        vm.getWarehouses()
        binding.spnWarehouses.prompt=getString(R.string.spinner_warehouse)
        vm.warehouses.observe(this, Observer {
            Log.e("warehouses", it.result.message)
            if (it.result.response?.warehouseVirtuals?.data!=null){
                //binding.spnWarehouses.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, it.result.response?.warehouseVirtuals?.data!!.map { it.whvirtualName })
                Log.e("wh",spnAdapter.toString())
                Log.e("wh",spnAdapter.count.toString())
                //Log.e("wh",it.result.response?.warehouseVirtuals?.data!!.map { it.whvirtualName }.toString())
                spnAdapter.clear()
                spnAdapter.addAll(it.result.response?.warehouseVirtuals?.data!!.map { it.whvirtualName })
                //spnAdapter.add(spinnerhint)
                Log.e("wh",spnAdapter.count.toString())
                Log.e("wh",list.toString())
                //binding.spnWarehouses.setSelection(binding.spnWarehouses.adapter.count)
                spnAdapter.notifyDataSetChanged()
                Log.e("whnot",spnAdapter.count.toString())
                //Log.e("wh",spnAdapter.getItem(spnAdapter.count).toString())


            }
        })
        binding.spnWarehouses.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                if ((view as TextView).text.toString()!=spinnerhint)
                    vm.chooseWarehouses((view as TextView).text.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }




        binding.buttonWrhScan.setOnClickListener { scanStart() }


    }

    @ExperimentalGetImage
    fun scanStart(){
        ScannerActivity.start(this)
    }

    companion object{
        fun start(context: Context){
            val intent = Intent(context, WarehouseActivity::class.java)
            context.startActivity(intent)
        }
    }
}
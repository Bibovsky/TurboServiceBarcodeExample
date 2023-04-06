package com.basgroup.turboservicebarcode.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.basgroup.turboservicebarcode.R
import com.basgroup.turboservicebarcode.data.dbmodels.ArticleSelectItem

class MainAdapter (val items: MutableList<ArticleSelectItem?>) : RecyclerView.Adapter<MainAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val codeManuf = itemView.findViewById<TextView>(R.id.rv_scan_code_manufacturer)
        val barcode = itemView.findViewById<TextView>(R.id.rv_scan_barcode)
        val btnEdit = itemView.findViewById<Button>(R.id.rv_scan_edit)
        val amount = itemView.findViewById<TextView>(R.id.rv_scan_amount)
        val counter = itemView.findViewById<TextView>(R.id.rv_scan_counter)
        val btnPlus = itemView.findViewById<Button>(R.id.rv_scan_plus)
        val btnMinus = itemView.findViewById<Button>(R.id.rv_scan_minus)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.rv_scan_main_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: MainAdapter.ViewHolder, position: Int) {

        var item = items?.get(position)
        viewHolder.codeManuf
        val textViewCodeManuf = viewHolder.codeManuf
        val textViewBarcode = viewHolder.barcode
        val textViewName = viewHolder.btnEdit
        val textViewAmountPlace = viewHolder.amount
        val textViewCounter = viewHolder.counter


        textViewCodeManuf.text="${item?.code} ${item?.producer}"
        textViewBarcode.text = item?.barcode
        textViewName.text = item?.name
        textViewAmountPlace.text = "Числится: ${item?.count} / ${item?.place}"


        viewHolder.btnPlus.setOnClickListener {
            var c = textViewCounter.text.toString().toInt()
            c += 1
            item?.count=c
            textViewCounter.text=c.toString()
        }
        viewHolder.btnMinus.setOnClickListener {
            var c = textViewCounter.text.toString().toInt()
            if (c>0)
                c -= 1
            item?.count=c
            textViewCounter.text=c.toString()
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}

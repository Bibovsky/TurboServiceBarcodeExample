package com.basgroup.turboservicebarcode.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.basgroup.turboservicebarcode.R
import com.basgroup.turboservicebarcode.data.dbmodels.ArticleSelectItem
import com.basgroup.turboservicebarcode.ui.foundItems.FoundItemsActivity

class foundItemAdapter (val items: MutableList<ArticleSelectItem>,val act:FoundItemsActivity) : RecyclerView.Adapter<foundItemAdapter.ViewHolder>()
{

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val codeManuf = itemView.findViewById<TextView>(R.id.rv_found_code_manufacturer)
        val barcode = itemView.findViewById<TextView>(R.id.rv_found_barcode)
        val name = itemView.findViewById<TextView>(R.id.rv_found_name)
        val amountPlace = itemView.findViewById<TextView>(R.id.rv_found_amount_where)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): foundItemAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.rv_found_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: foundItemAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val textViewCodeManuf = viewHolder.codeManuf
        val textViewBarcode = viewHolder.barcode
        val textViewName = viewHolder.name
        val textViewAmountPlace = viewHolder.amountPlace
        textViewCodeManuf.text = "${item.code} ${item.producer}"
        textViewBarcode.text = item.barcode
        textViewName.text = item.name
        textViewAmountPlace.text = "Числится: ${item.count} / ${item.place}"

        viewHolder.itemView.setOnClickListener { act.itemChosen(ArticleSelectItem(item.articleId,item.code,item.producer,item.barcode,item.name,item.count,item.place,0)) }
    }


    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return items.size
    }

}

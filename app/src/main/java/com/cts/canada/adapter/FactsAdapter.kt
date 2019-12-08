package com.cts.canada.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cts.canada.R
import com.cts.canada.model.FactsRowItem
import com.bumptech.glide.Glide

class FactsAdapter(val context: Context) : RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {

    private var factsListData: List<FactsRowItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        return FactsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.facts_row_item, parent, false))
     }


    override fun getItemCount(): Int {
                      return factsListData.size
    }


    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        var rowItem = factsListData.get(position)
        holder.title.setText(rowItem.title)
        holder.description.setText(rowItem.description)
        Glide.with(context)
            .asBitmap()
            .load(rowItem.imageHref)
            .into(holder.image)
    }


    class FactsViewHolder (view: View) : RecyclerView.ViewHolder(view)
    {
        val title : TextView = view.findViewById(R.id.title)
        val description : TextView = view.findViewById(R.id.description)
        val image : ImageView = view.findViewById(R.id.hpref)
    }

    // region Public Functions
    fun setFactsList(factsListData: ArrayList<FactsRowItem>) {
        this.factsListData = factsListData
        notifyDataSetChanged()
    }
}
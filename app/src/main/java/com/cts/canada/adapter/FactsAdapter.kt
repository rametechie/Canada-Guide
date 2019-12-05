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

class FactsAdapter(val factsRow : ArrayList<FactsRowItem>, val context: Context) : RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        return FactsViewHolder(LayoutInflater.from(context).inflate(R.layout.facts_row_item, parent, false))
     }


    override fun getItemCount(): Int {
                      return factsRow.size
    }


    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        var rowItem = factsRow.get(position)
        holder.title.setText(rowItem.title)
        holder.description.setText(rowItem.description)

    }


    class FactsViewHolder (view: View) : RecyclerView.ViewHolder(view)
    {
        val title : TextView = view.findViewById(R.id.title)
        val description : TextView = view.findViewById(R.id.description)
        val image : ImageView = view.findViewById(R.id.hpref)
    }
}
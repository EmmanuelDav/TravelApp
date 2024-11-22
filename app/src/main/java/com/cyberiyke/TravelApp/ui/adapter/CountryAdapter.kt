package com.cyberiyke.TravelApp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyberiyke.TravelApp.R

class CountryAdapter(private var countryList: List<String>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.countryNameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.counties_item_view, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.countryName.text = countryList[position]
    }

    override fun getItemCount(): Int = countryList.size

    fun updateList(newList: List<String>) {
        countryList = newList
        notifyDataSetChanged()
    }
}

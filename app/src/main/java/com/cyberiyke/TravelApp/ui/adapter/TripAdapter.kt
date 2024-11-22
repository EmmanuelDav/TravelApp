package com.cyberiyke.TravelApp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyberiyke.TravelApp.data.model.TripDetail
import com.cyberiyke.TravelApp.databinding.LayoutItemTravelBinding

/**
 * Created by Emmanuel Iyke  on 3/7/2024.
 */
class TripAdapter()
    : RecyclerView.Adapter<TripAdapter.HomeViewHolder>() {

    private var mainArticleList = mutableListOf<TripDetail>()
    private var searchResultsList = mutableListOf<TripDetail>()
    private var isSearchMode = false


    var trips: List<TripDetail>
        get() = if (isSearchMode) searchResultsList else mainArticleList
        set(value) {
            mainArticleList = value.toMutableList() // Update main article list
            if (!isSearchMode) {
                notifyDataSetChanged() // Refresh only if not in search mode
            }
        }

    // Method to set search results and switch to search mode
    fun setSearchResults(results: List<TripDetail>) {
        searchResultsList = results.toMutableList()
        isSearchMode = true
        notifyDataSetChanged()
    }

    fun exitSearchMode() {
        isSearchMode = false
        searchResultsList.clear()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutItemTravelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = trips.count()

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(trips[position])
    }

    inner class HomeViewHolder(private val binding: LayoutItemTravelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trip: TripDetail) = with(itemView) {
            binding.tripDate.text = trip.date
            binding.tripTitle.text = trip.tripName
            binding.tripLocation.text = trip.location
            binding.tripDuration.text = trip.tripStyle
        }
    }


}



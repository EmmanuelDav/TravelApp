package com.cyberiyke.TravelApp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyberiyke.TravelApp.data.local.TripEntity
import com.cyberiyke.TravelApp.data.model.TripDetail
import com.cyberiyke.TravelApp.databinding.LayoutItemNewsBinding

/**
 * Created by Emmanuel Iyke  on 3/7/2024.
 */
class TripAdapter<T : ViewModel>(private val viewModel: T, private val listener: ((TripDetail) -> Unit)? = null)
    : RecyclerView.Adapter<TripAdapter<T>.HomeViewHolder>() {

    private var mainArticleList = mutableListOf<TripDetail>()
    private var searchResultsList = mutableListOf<TripDetail>()
    private var isSearchMode = false


    var articles: List<TripDetail>
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
        val view = LayoutItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = articles.count()

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    inner class HomeViewHolder(private val binding: LayoutItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: TripDetail) = with(itemView) {
//            binding.articleTitle.text = article.articleTitle
//            binding.articleDescription.text = article.articleDescription
//            binding.articleDateTime.text = article.publisedAt
//            binding.articleSource.text = article.articleSource
//            updateFavoriteIcon(article.isFavorite,binding)
//            Glide.with(this)
//                .load(article.articleUrlToImage)
//                .placeholder(R.drawable.img_placeholder)
//                .error(R.drawable.img_placeholder)
//                .into(binding.articleImage)
//            setOnClickListener {
//                listener?.invoke(articles[layoutPosition])
//            }
//            binding.favoriteButton.setOnClickListener {
//                val newFavState = !article.isFavorite
//                article.isFavorite = newFavState
//                updateFavoriteIcon(newFavState, binding)
//
//                if (viewModel is HomeViewModel){
//                    (viewModel as HomeViewModel).updateToggle(article.id, newFavState)
//                } else if (viewModel is FavouriteViewModel) {
//                    (viewModel as FavouriteViewModel).updateToggle(article.id, newFavState)
//                }
//
//                if (isSearchMode)  (viewModel as HomeViewModel).saveArticleFromSearch( newFavState, article)
//            }
        }
    }


}



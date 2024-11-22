package com.cyberiyke.TravelApp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips_travel")
data class TripEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var articleUrl: String = "",
    var articleTitle: String = "",
    var publisedAt: String= "",
    var articleDescription: String = "",
    var articleDateTime: String = "",
    var articleSource: String = "",
    var articleUrlToImage: String = "",
    var isFavorite : Boolean = false // checks if it is favourited

    )
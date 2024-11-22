package com.cyberiyke.TravelApp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyberiyke.TravelApp.data.model.Hotels

@Entity(tableName = "trips_travel")
data class TripEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var departureLocation: String = "",
    var destinationLocation: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var tripInfo: String = "",

    )
package com.cyberiyke.TravelApp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(article: List<com.cyberiyke.TravelApp.data.local.TripEntity>)

    @Query("SELECT * FROM trips_travel ORDER BY endDate DESC")
    fun getAllTrips(): LiveData<List<com.cyberiyke.TravelApp.data.local.TripEntity>>


}

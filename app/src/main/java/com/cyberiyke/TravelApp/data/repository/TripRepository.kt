package com.cyberiyke.TravelApp.data.repository

import com.cyberiyke.TravelApp.data.model.TripDetail
import com.cyberiyke.TravelApp.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllTrips() = apiService.getAllTrips()

    suspend fun getTripById(id: String) = apiService.getTripById(id)

    suspend fun createTrip(tripDetail: TripDetail) = apiService.createTrip(tripDetail)

    suspend fun updateTrip(id: String, tripDetail: TripDetail) = apiService.updateTrip(id, tripDetail)

    suspend fun partiallyUpdateTrip(id: String, updates: Map<String, Any>) = apiService.partiallyUpdateTrip(id, updates)

    suspend fun deleteTrip(id: String) = apiService.deleteTrip(id)

}

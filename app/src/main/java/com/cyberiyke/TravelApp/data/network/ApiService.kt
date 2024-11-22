package com.cyberiyke.TravelApp.data.network

import com.cyberiyke.TravelApp.data.model.TripDetail
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*


interface ApiService {

    @GET("/api/traveldetail/")
    suspend fun getAllTrips(): List<TripDetail>

    @GET("/api/traveldetail/{id}")
    suspend fun getTripById(@Path("id") id: String): TripDetail

    @POST("/api/traveldetail/")
    suspend fun createTrip(@Body tripDetail: TripDetail): TripDetail

    @PUT("/api/traveldetail/{id}")
    suspend fun updateTrip(@Path("id") id: String, @Body tripDetail: TripDetail): TripDetail

    @PATCH("/api/traveldetail/{id}")
    suspend fun partiallyUpdateTrip(@Path("id") id: String, @Body updates: Map<String, Any>): TripDetail

    @DELETE("/api/traveldetail/{id}")
    suspend fun deleteTrip(@Path("id") id: String): String
}

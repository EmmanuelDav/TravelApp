package com.cyberiyke.TravelApp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberiyke.TravelApp.data.model.TripDetail
import com.cyberiyke.TravelApp.data.network.NetworkResult
import com.cyberiyke.TravelApp.data.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val repository: TripRepository
) : ViewModel() {

    private val _tripList = MutableLiveData<NetworkResult<List<TripDetail>>>()
    val tripList: LiveData<NetworkResult<List<TripDetail>>> = _tripList

    private val _trip = MutableLiveData<NetworkResult<TripDetail>>()
    val trip: LiveData<NetworkResult<TripDetail>> = _trip


    fun fetchAllTrips() = viewModelScope.launch {
        _tripList.value = NetworkResult.Loading() // Emit Loading state

        try {
            val value = repository.getAllTrips()
            _tripList.value = NetworkResult.Success(value) // Emit Success state
        } catch (e: Exception) {
            _tripList.value = NetworkResult.Error(e.message ?: "Unknown error occurred") // Emit Error state
        }
    }

    fun fetchTripById(id: String) = viewModelScope.launch {
        _trip.value = NetworkResult.Loading() // Emit Loading state

        try {
            var value = repository.getTripById(id)
            _trip.value = NetworkResult.Success(value)
        } catch (e: Exception) {
            _trip.value = NetworkResult.Error(e.message?:"Unknown error")
        }
    }

    fun createNewTrip(tripDetail: TripDetail) = viewModelScope.launch {
        _trip.value = NetworkResult.Loading()
        try {
           val value =  repository.createTrip(tripDetail)
            _trip.value = NetworkResult.Success(value)
        } catch (e: Exception) {
            _trip.value = NetworkResult.Error("Failed to create trip: ${e.message}")
        }
    }

    fun updateTrip(id: String, tripDetail: TripDetail) = viewModelScope.launch {
        _trip.value = NetworkResult.Loading()
        try {
            val value  = repository.updateTrip(id, tripDetail)
            _trip.value = NetworkResult.Success(value)
        } catch (e: Exception) {
            _trip.value = NetworkResult.Error("Failed to update trip: ${e.message}")
        }
    }

    fun partiallyUpdateTrip(id: String, updates: Map<String, Any>) = viewModelScope.launch {
        _trip.value = NetworkResult.Loading()
        try {
            val value = repository.partiallyUpdateTrip(id, updates)
            _trip.value = NetworkResult.Success(value)
        } catch (e: Exception) {
            _trip.value = NetworkResult.Error("Failed to partially update trip: ${e.message}")
        }
    }

    fun deleteTrip(id: String) = viewModelScope.launch {
        _trip.value = NetworkResult.Loading()
        try {
            val value = repository.deleteTrip(id)
           // _trip.value = NetworkResult.Success(value)
        } catch (e: Exception) {
            _trip.value = NetworkResult.Error("Failed to delete trip: ${e.message}")
        }
    }
}

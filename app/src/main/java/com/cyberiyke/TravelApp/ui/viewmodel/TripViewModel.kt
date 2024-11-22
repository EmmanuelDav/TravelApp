package com.cyberiyke.TravelApp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberiyke.TravelApp.data.model.TripDetail
import com.cyberiyke.TravelApp.data.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val repository: TripRepository
) : ViewModel() {

    private val _tripList = MutableLiveData<List<TripDetail>>()
    val tripList: LiveData<List<TripDetail>> = _tripList

    private val _trip = MutableLiveData<TripDetail>()
    val trip: LiveData<TripDetail> = _trip

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun fetchAllTrips() = viewModelScope.launch {
        try {
            _tripList.value = repository.getAllTrips()
        } catch (e: Exception) {
            _status.value = "Failed to fetch trips: ${e.message}"
        }
    }

    fun fetchTripById(id: String) = viewModelScope.launch {
        try {
            _trip.value = repository.getTripById(id)
        } catch (e: Exception) {
            _status.value = "Failed to fetch trip: ${e.message}"
        }
    }

    fun createNewTrip(tripDetail: TripDetail) = viewModelScope.launch {
        try {
            repository.createTrip(tripDetail)
            _status.value = "Trip created successfully!"
        } catch (e: Exception) {
            _status.value = "Failed to create trip: ${e.message}"
        }
    }

    fun updateTrip(id: String, tripDetail: TripDetail) = viewModelScope.launch {
        try {
            repository.updateTrip(id, tripDetail)
            _status.value = "Trip updated successfully!"
        } catch (e: Exception) {
            _status.value = "Failed to update trip: ${e.message}"
        }
    }

    fun partiallyUpdateTrip(id: String, updates: Map<String, Any>) = viewModelScope.launch {
        try {
            repository.partiallyUpdateTrip(id, updates)
            _status.value = "Trip partially updated successfully!"
        } catch (e: Exception) {
            _status.value = "Failed to partially update trip: ${e.message}"
        }
    }

    fun deleteTrip(id: String) = viewModelScope.launch {
        try {
            repository.deleteTrip(id)
            _status.value = "Trip deleted successfully!"
        } catch (e: Exception) {
            _status.value = "Failed to delete trip: ${e.message}"
        }
    }
}

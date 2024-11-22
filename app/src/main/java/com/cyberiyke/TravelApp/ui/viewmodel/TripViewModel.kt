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

    private val _status = MutableLiveData<NetworkResult<String>>()
    val status: LiveData<NetworkResult<String>> = _status

    private val _tripList = MutableLiveData<NetworkResult<List<TripDetail>>>()
    val tripList: LiveData<NetworkResult<List<TripDetail>>> = _tripList

    private val _trip = MutableLiveData<NetworkResult<TripDetail>>()
    val trip: LiveData<NetworkResult<TripDetail>> = _trip

    fun fetchAllTrips() = viewModelScope.launch {
        _tripList.value = NetworkResult.Loading()
        try {
            val value =  repository.getAllTrips()
            _tripList.value = NetworkResult.Success(value)
        } catch (e: Exception) {
            _tripList.value = NetworkResult.Error("Failed to fetch trip: ${e.message}")
        }
    }

    fun fetchTripById(id: String) = viewModelScope.launch {
        _status.value = NetworkResult.Loading()
        try {
            repository.getTripById(id)
            _status.value = NetworkResult.Success("fetching Tip by Id successfully!")
        } catch (e: Exception) {
            _status.value = NetworkResult.Error("Failed to fetch trip: ${e.message}")
        }
    }


    fun createNewTrip(tripDetail: TripDetail) = viewModelScope.launch {
        _status.value = NetworkResult.Loading()
        try {
            repository.createTrip(tripDetail)
            _status.value = NetworkResult.Success("Trip created successfully!")
        } catch (e: Exception) {
            _status.value = NetworkResult.Error("Failed to create trip: ${e.message}")
        }
    }

    fun updateTrip(id: String, tripDetail: TripDetail) = viewModelScope.launch {
        _status.value = NetworkResult.Loading()
        try {
            repository.updateTrip(id, tripDetail)
            _status.value = NetworkResult.Success("Trip updated successfully!")
        } catch (e: Exception) {
            _status.value = NetworkResult.Error("Failed to update trip: ${e.message}")
        }
    }

    fun partiallyUpdateTrip(id: String, updates: Map<String, Any>) = viewModelScope.launch {
        _status.value = NetworkResult.Loading()
        try {
            repository.partiallyUpdateTrip(id, updates)
            _status.value = NetworkResult.Success("Trip partially updated successfully!")
        } catch (e: Exception) {
            _status.value = NetworkResult.Error("Failed to partially update trip: ${e.message}")
        }
    }

    fun deleteTrip(id: String) = viewModelScope.launch {
        _status.value = NetworkResult.Loading()
        try {
            repository.deleteTrip(id)
            _status.value = NetworkResult.Success("Trip deleted successfully!")
        } catch (e: Exception) {
            _status.value = NetworkResult.Error("Failed to delete trip: ${e.message}")
        }
    }
}

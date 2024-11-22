package com.cyberiyke.TravelApp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cyberiyke.TravelApp.data.model.TripDetail

class MainViewModel : ViewModel() {


    val flightLocation = MutableLiveData<String>()
    val flightDate = MutableLiveData<String>()
    val tripName = MutableLiveData<String>()
    val tripStyle = MutableLiveData<String>()
    val tripDescription = MutableLiveData<String>()

    fun getTripDetails(): TripDetail {
        return TripDetail(
            flightLocation.value ?: "",
            flightDate.value ?: "",
            tripName.value ?: "",
            tripStyle.value ?: "",
            tripDescription.value ?: ""
        )
    }


}
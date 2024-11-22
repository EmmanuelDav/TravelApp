package com.cyberiyke.TravelApp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cyberiyke.TravelApp.data.model.TripDetail

class MainViewModel: ViewModel() {

        val flightLocation = MutableLiveData<String>()
        val flightDate = MutableLiveData<String>()
        val tripInfo = MutableLiveData<String>()

        fun getTripDetails(): TripDetail {
            return TripDetail(
                flightLocation.value ?: "",
                flightDate.value ?: "",
                tripInfo.value ?: ""
            )
        }



}
package com.cyberiyke.TravelApp.ui.flightLocaton

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyberiyke.TravelApp.R

class flightLocation : Fragment() {

    companion object {
        fun newInstance() = flightLocation()
    }

    private val viewModel: FlightLocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_flight_location, container, false)
    }
}
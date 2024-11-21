package com.cyberiyke.TravelApp.ui.flightInfo

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyberiyke.TravelApp.R

class FlightInfo : Fragment() {

    companion object {
        fun newInstance() = FlightInfo()
    }

    private val viewModel: FlightInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_flight_info, container, false)
    }
}
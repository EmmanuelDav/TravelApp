package com.cyberiyke.TravelApp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.cyberiyke.TravelApp.R

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<ImageButton>(R.id.loadButtonSheets).setOnClickListener {
            findNavController().navigate(R.id.flightLocationBottomSheet)
        }
        return view
    }

}
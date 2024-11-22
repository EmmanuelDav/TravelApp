package com.cyberiyke.TravelApp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyberiyke.TravelApp.R
import com.cyberiyke.TravelApp.data.network.NetworkResult
import com.cyberiyke.TravelApp.databinding.FragmentHomeBinding
import com.cyberiyke.TravelApp.ui.adapter.TripAdapter
import com.cyberiyke.TravelApp.ui.viewmodel.TripViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: TripViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var tripAdapter: TripAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.loadButtonSheets.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_flightLocationBottomSheet)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.fetchAllTrips()
        tripAdapter = TripAdapter()


        viewModel.tripList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is NetworkResult.Loading -> {


                }
                is NetworkResult.Success -> {
                    binding.tripRecyclerview.layoutManager = LinearLayoutManager(activity)
                    binding.tripRecyclerview.adapter = tripAdapter
                    tripAdapter.trips = result.data
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}
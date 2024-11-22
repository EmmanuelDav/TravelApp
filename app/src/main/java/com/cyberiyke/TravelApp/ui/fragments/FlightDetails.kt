package com.cyberiyke.TravelApp.ui.fragments

import android.app.ProgressDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cyberiyke.TravelApp.R
import com.cyberiyke.TravelApp.data.model.TripDetail
import com.cyberiyke.TravelApp.data.network.NetworkResult
import com.cyberiyke.TravelApp.databinding.FragmentFlightDetailsBinding
import com.cyberiyke.TravelApp.databinding.FragmentFlightInfoBinding
import com.cyberiyke.TravelApp.ui.viewmodel.TripViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightDetails : Fragment() {

    private lateinit var binding:FragmentFlightDetailsBinding

    companion object {
        fun newInstance() = FlightDetails()
    }

    private val viewModel: TripViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightDetailsBinding.inflate(layoutInflater)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tripDetail = arguments?.getParcelable<TripDetail>("tripDetail")

        tripDetail.let{
            binding.tripDate.text = it!!.date
            binding.tripdestination.text = it.tripInfo
            binding.tripLocation.text = "${it.location}"
        }



        binding.addActivitiesButton.setOnClickListener {
            val progressDialog = ProgressDialog(requireContext())
            progressDialog.setMessage("Loading data...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            viewModel.createNewTrip(tripDetail!!)

            viewModel.status.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Success -> {
                        Toast.makeText(requireContext(), result.data, Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                        progressDialog.dismiss()
                    }
                }
            }


        }


    }

}
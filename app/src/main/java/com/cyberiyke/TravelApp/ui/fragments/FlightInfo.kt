package com.cyberiyke.TravelApp.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.cyberiyke.TravelApp.R
import com.cyberiyke.TravelApp.data.model.TripDetail
import com.cyberiyke.TravelApp.databinding.FragmentFlightInfoBinding
import com.cyberiyke.TravelApp.ui.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FlightInfo : BottomSheetDialogFragment() {


    private lateinit var binding: FragmentFlightInfoBinding

    companion object {
        fun newInstance() = FlightDetails()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightInfoBinding.inflate(layoutInflater)
        binding.next.setOnClickListener {
            if (binding.tripName.text?.isEmpty() == true || binding.tripStyle.text?.isEmpty() == true || binding.tripDescription.text?.isEmpty() == true) {
                Toast.makeText(requireContext(), "Empty Field found", Toast.LENGTH_SHORT).show()
            } else {
                val tripDetail = arguments?.getParcelable<TripDetail>("tripDetail")
                tripDetail?.tripInfo = binding.tripDescription.text.toString()
                tripDetail?.tripStyle = binding.tripStyle.text.toString()
                tripDetail?.tripName = binding.tripName.text.toString()
                val bundle = Bundle().apply {
                    putParcelable("tripDetail", tripDetail)
                }
                findNavController().navigate(
                    R.id.action_tripInfoBottomSheet_to_flightDetails,
                    bundle
                )
            }
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }
}
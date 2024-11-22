package com.cyberiyke.TravelApp.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.cyberiyke.TravelApp.R
import com.cyberiyke.TravelApp.data.model.TripDetail
import com.cyberiyke.TravelApp.ui.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FlightDatePicker : BottomSheetDialogFragment() {

    private lateinit var startDateCalendarView: CalendarView
    private lateinit var endDateCalendarView: CalendarView
    private lateinit var selectedStartDate: TextView
    private lateinit var selectedEndDate: TextView


    companion object {
        fun newInstance() = FlightDatePicker()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =  inflater.inflate(R.layout.fragment_flight_date_picker, container, false)

        startDateCalendarView = view.findViewById(R.id.startDateCalendarView)
        endDateCalendarView = view.findViewById(R.id.endDateCalendarView)
        selectedStartDate = view.findViewById(R.id.selectedStartDate)
        selectedEndDate = view.findViewById(R.id.selectedEndDate)
        var cancel = view.findViewById<ImageButton>(R.id.cancel_button)
        var next  = view.findViewById<Button>(R.id.next)

        startDateCalendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = formatDate(year, month, dayOfMonth)
            selectedStartDate.text =  selectedDate
        }

        // Handle End Date selection
        endDateCalendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = formatDate(year, month, dayOfMonth)
            selectedEndDate.text = selectedDate


        }

        next.setOnClickListener {
            if (selectedEndDate.text=="Select a date"  || selectedStartDate.text == "Select a date"){
                Toast.makeText(
                    requireContext(),
                    "put  your date",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
               val tripDetail = TripDetail(date = "${selectedStartDate.text} ${selectedEndDate.text.toString()}")
                val bundle = Bundle().apply {
                    putParcelable("tripDetail", tripDetail)
                }
                findNavController().navigate(R.id.action_flightDateBottomSheet_to_tripInfoBottomSheet, bundle)




            }
        }

        cancel.setOnClickListener {
            dismiss()
        }

        return view

    }


    // Helper function to format date
    private fun formatDate(year: Int, month: Int, dayOfMonth: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val format = SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
        return format.format(calendar.time)
    }

}
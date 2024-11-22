package com.cyberiyke.TravelApp.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyberiyke.TravelApp.R
import com.cyberiyke.TravelApp.ui.adapter.CountryAdapter
import com.cyberiyke.TravelApp.ui.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class flightLocation : BottomSheetDialogFragment() {


    private val viewModel: MainViewModel by viewModels()
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var allCountries: List<String>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view  =  inflater.inflate(R.layout.fragment_flight_location, container, false)

        val searchBox = view.findViewById<EditText>(R.id.searchBox)
        val recyclerView = view.findViewById<RecyclerView>(R.id.countryRecyclerView)

        // Initialize country data
        allCountries = getCountries()
        countryAdapter = CountryAdapter(allCountries)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = countryAdapter

        // Search filter
        searchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val filteredList = allCountries.filter {
                    it.contains(s.toString(), ignoreCase = true)
                }
                countryAdapter.updateList(filteredList)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        return view
    }

    private fun getCountries(): List<String> {
        val isoCountryCodes = Locale.getISOCountries()
        val countriesWithEmojis = ArrayList<String>()
        for (countryCode in isoCountryCodes) {
            val locale = Locale("", countryCode)
            val countryName = locale.displayCountry
            val flagOffset = 0x1F1E6
            val asciiOffset = 0x41
            val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
            val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
            val flag = String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
            countriesWithEmojis.add("$countryName           $flag")
        }
        return countriesWithEmojis
    }

}
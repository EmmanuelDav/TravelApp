package com.cyberiyke.TravelApp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cyberiyke.TravelApp.R
import com.cyberiyke.TravelApp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


       val navController = findNavController(R.id.nav_host_fragment)

        binding.loadButtonSheets.setOnClickListener {
            navController.navigate(R.id.flightLocationBottomSheet)
        }

    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            Toast.makeText(this, "ttt", Toast.LENGTH_SHORT).show()
            fragmentManager.popBackStack() // Navigate back to the previous fragment or activity
        } else {
            super.onBackPressed() // Default behavior: exit the app
        }
    }


}
package com.cyberiyke.TravelApp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class NewApiApplication: Application(){

    override fun onCreate() {
        super.onCreate()
    }

}
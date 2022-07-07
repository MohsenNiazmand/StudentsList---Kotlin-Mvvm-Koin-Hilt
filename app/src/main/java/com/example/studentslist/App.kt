package com.example.studentslist

import android.app.Application
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())


    }



}

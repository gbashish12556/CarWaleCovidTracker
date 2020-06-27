package com.example.carwalecovidtracker

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        intialiseSharedPref()
    }

    companion object {

        lateinit var sharedPref: SharedPreferences
        lateinit var appContext: Context
        fun intialiseSharedPref(){
            sharedPref = appContext.getSharedPreferences("Default", Context.MODE_PRIVATE)
        }

    }
}
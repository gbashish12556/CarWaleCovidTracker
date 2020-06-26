package com.example.carwalecovidtracker

import com.example.carwalecovidtracker.pojo.CovidResponse
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @get:GET("/summary")
    val covidData: Call<CovidResponse>

}
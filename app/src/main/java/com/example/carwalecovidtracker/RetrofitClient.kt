package com.example.carwalecovidtracker

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    private val retrofit: Retrofit

    val api: ApiService
        get() = retrofit.create(ApiService::class.java)

    init {

        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    companion object {

        private val BASE_URL = "https://api.covid19api.com"
        private var mRetrofitInstance: RetrofitClient? = null

        val instance: RetrofitClient
            @Synchronized get() {

                if (mRetrofitInstance == null) {
                    mRetrofitInstance = RetrofitClient()
                }

                return mRetrofitInstance as RetrofitClient

            }
    }

}
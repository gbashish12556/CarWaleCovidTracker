package com.example.carwalecovidtracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.carwalecovidtracker.pojo.CovidResponse
import java.util.ArrayList

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val apiRepository: ApiRepository

    val allCases: MutableLiveData<CovidResponse>
        get() = apiRepository.allCovidData


    init {
        apiRepository = ApiRepository()
    }


}
package com.example.carwalecovidtracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.carwalecovidtracker.pojo.*
import java.util.ArrayList

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val apiRepository: ApiRepository

    val globalData: MutableLiveData<GlobalData>
        get() = apiRepository.allGlobalData

    val allCountryWiseCases: MutableLiveData<ArrayList<CountryData>>
        get() = apiRepository.allCountryWiseList

    fun sortData(sortData: SortData){
        apiRepository.sortData(sortData)
    }

    fun filterData(filteData: FilterData){
        apiRepository.filterData(filteData)
    }

    init {
        apiRepository = ApiRepository()
    }


}
package com.example.carwalecovidtracker

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.carwalecovidtracker.pojo.*
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class ApiRepository {

    private val messageApiStatus = MutableLiveData<Boolean>()
    private val globaldata = MutableLiveData<GlobalData>()
    private val countryWiseList = MutableLiveData<ArrayList<CountryData>>()
    private var countryList:ArrayList<CountryData>? = null
    private var comparators = Comparators()

    val allGlobalData: MutableLiveData<GlobalData>
        get() {
            fetchCovidData()
            return globaldata
        }


    val allCountryWiseList: MutableLiveData<ArrayList<CountryData>>
        get() {
            return countryWiseList
        }



    fun fetchCovidData() {

        val call1 = RetrofitClient.instance.api.covidData

        call1.enqueue(object : Callback<CovidResponse> {

            override fun onResponse(call: Call<CovidResponse>, response: Response<CovidResponse>) {
                if (response.code() == 200) {
                    val covidResponse = response.body()
                    if (covidResponse !=  null) {

                        globaldata.postValue(covidResponse.globalData)
                        countryList = covidResponse.countryData
                        countryWiseList.postValue(countryList)
                        filterData(FilterData(Constant.FILTER_TYPE_GRT,Constant.SORT_COLUMN_TOTAL_CASES,0))

                    } else {

                        messageApiStatus!!.postValue(false)

                    }

                } else {
                    messageApiStatus!!.postValue(false)
                }
            }

            override fun onFailure(call: Call<CovidResponse>, t: Throwable) {
                messageApiStatus!!.value = false
            }
        })
    }

    fun sortData(sortData:SortData){

        when(sortData!!.sortType){

            Constant.SORT_TYPE_ASC->{

                when(sortData!!.sortField){
                    Constant.SORT_COLUMN_COUNTRY->{

                        Collections.sort(countryList,comparators.CountryAsc())
                    }
                    Constant.SORT_COLUMN_TOTAL_CASES->{
                        Collections.sort(countryList,comparators.TotalConfirmedAsc())
                    }
                    Constant.SORT_COLUMN_DEATHS->{
                        Collections.sort(countryList,comparators.TotalDeathsAsc())
                    }
                    Constant.SORT_COLUMN_RECOVERED->{
                        Collections.sort(countryList,comparators.TotalRecoveredAsc())
                    }
                }

            }

            Constant.SORT_TYPE_DESC->{

                when(sortData!!.sortField){

                    Constant.SORT_COLUMN_COUNTRY->{
                        Collections.sort(countryList,comparators.CountryDesc())
                    }
                    Constant.SORT_COLUMN_TOTAL_CASES->{
                        Collections.sort(countryList,comparators.TotalConfirmedDesc())
                    }
                    Constant.SORT_COLUMN_DEATHS->{
                        Collections.sort(countryList,comparators.TotalDeathsDesc())
                    }
                    Constant.SORT_COLUMN_RECOVERED->{
                        Collections.sort(countryList,comparators.TotalRecoveredDesc())
                    }

                }

            }

        }
        this.countryWiseList.postValue(countryList)
    }

    fun resetFilter(){

        var newGlobalData = GlobalData(0,0,0)

        for(i in 0..countryList!!.size-1){
            newGlobalData.totalConfirmed += countryList!![i].totalConfirmed
            newGlobalData.totalDeaths += countryList!![i].totalDeaths
            newGlobalData.totalRecovered += countryList!![i].totalRecovered
        }

        globaldata.postValue(newGlobalData)
        allCountryWiseList.postValue(countryList as ArrayList<CountryData>?)
        addFilter(FilterData("","",0))
    }
    fun filterData(filterData:FilterData){
        var newCountryList = mutableListOf<CountryData>()
        var newGlobalData = GlobalData(0,0,0)
        when(filterData.filterType){
            Constant.FILTER_TYPE_GRT->{
                when(filterData.filterField){
                    Constant.SORT_COLUMN_TOTAL_CASES->{
                        for(i in 0..countryList!!.size-1){
                            var usersCountry = App.sharedPref.getString(Constant.COUNTRY_NAME,"None")!!.toUpperCase() as CharSequence
                            if(usersCountry  in countryList!!.get(i).countryCode.toUpperCase()){
                                var data = countryList!!.get(i).copy()
                                newCountryList!!.add(0,data)
                            }else if(countryList!!.get(i).totalConfirmed >= filterData.filterValue){
                                newCountryList.add(countryList!!.get(i))
                            }
                        }
                        if(filterData.filterValue  == 0){
                            countryList = newCountryList as ArrayList<CountryData>
                        }
                    }
                    Constant.SORT_COLUMN_DEATHS->{
                        for(i in 0..countryList!!.size-1){
                            if(countryList!!.get(i).totalDeaths >= filterData.filterValue){
                                newCountryList.add(countryList!!.get(i))
                            }
                        }
                    }
                    Constant.SORT_COLUMN_RECOVERED->{
                        for(i in 0..countryList!!.size-1){
                            if(countryList!!.get(i).totalRecovered >= filterData.filterValue){
                                newCountryList.add(countryList!!.get(i))
                            }
                        }
                    }
                }
            }
            Constant.FILTER_TYPE_LESS->{
                when(filterData.filterField){
                    Constant.SORT_COLUMN_TOTAL_CASES->{
                        for(i in 0..countryList!!.size-1){
                            if(countryList!!.get(i).totalConfirmed <= filterData.filterValue){
                                newCountryList.add(countryList!!.get(i))
                            }
                        }
                    }
                    Constant.SORT_COLUMN_DEATHS->{
                        for(i in 0..countryList!!.size-1){
                            if(countryList!!.get(i).totalDeaths <= filterData.filterValue){
                                newCountryList.add(countryList!!.get(i))
                            }
                        }
                    }
                    Constant.SORT_COLUMN_RECOVERED->{
                        for(i in 0..countryList!!.size-1){
                            if(countryList!!.get(i).totalRecovered <= filterData.filterValue){
                                newCountryList.add(countryList!!.get(i))
                            }
                        }
                    }
                }
            }
        }

        for(i in 0..newCountryList!!.size-1){
            newGlobalData.totalConfirmed += newCountryList!![i].totalConfirmed
            newGlobalData.totalDeaths += newCountryList!![i].totalDeaths
            newGlobalData.totalRecovered += newCountryList!![i].totalRecovered
        }

        globaldata.postValue(newGlobalData)
        allCountryWiseList.postValue(newCountryList as ArrayList<CountryData>?)
        addFilter(filterData)

    }

    fun addFilter(filterData:FilterData){

        val editor = App.sharedPref.edit()
        editor.putString(Constant.FILTER_RANGE_TYPE, filterData.filterType);
        editor.putString(Constant.FILTER_FIELD, filterData.filterField);
        editor.putInt(Constant.FILTER_VALUE, filterData.filterValue);
        editor.apply()

    }

}

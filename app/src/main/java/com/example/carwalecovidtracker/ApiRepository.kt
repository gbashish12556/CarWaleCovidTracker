package com.example.carwalecovidtracker

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.carwalecovidtracker.pojo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class ApiRepository {

    private val messageApiStatus = MutableLiveData<Boolean>()
    private val globaldata = MutableLiveData<GlobalData>()
    private val countryWiseList = MutableLiveData<ArrayList<CountryData>>()
    private var countryList:ArrayList<CountryData>? = null
    private var originalCountryList:ArrayList<CountryData>? = null

    val allGlobalData: MutableLiveData<GlobalData>
        get() {
            fetchCovidData()
            return globaldata
        }

    val apiStatus: MutableLiveData<Boolean>
        get() {
            return messageApiStatus
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
                        countryList?.sortBy { it.country }
                    }
                    Constant.SORT_COLUMN_TOTAL_CASES->{
                        countryList?.sortBy { it.totalConfirmed }
                    }
                    Constant.SORT_COLUMN_DEATHS->{
                        countryList?.sortBy { it.totalDeaths }
                    }
                    Constant.SORT_COLUMN_RECOVERED->{
                        countryList?.sortBy { it.totalRecovered }
                    }
                }

            }

            Constant.SORT_TYPE_DESC->{

                when(sortData!!.sortField){
                    Constant.SORT_COLUMN_COUNTRY->{
                        countryList?.sortByDescending { it.country }
                    }
                    Constant.SORT_COLUMN_TOTAL_CASES->{
                        countryList?.sortByDescending { it.totalConfirmed }
                    }
                    Constant.SORT_COLUMN_DEATHS->{
                        countryList?.sortByDescending { it.totalDeaths }
                    }
                    Constant.SORT_COLUMN_RECOVERED->{
                        countryList?.sortByDescending { it.totalRecovered }
                    }
                }

            }

        }
        this.countryWiseList.postValue(countryList)
    }

    fun resetFilter(){

        var newGlobalData = GlobalData(0,0,0)

        countryList?.forEach {
            newGlobalData.totalConfirmed += it.totalConfirmed
            newGlobalData.totalDeaths += it.totalDeaths
            newGlobalData.totalRecovered += it.totalRecovered
        }

        globaldata.postValue(newGlobalData)
        allCountryWiseList.postValue(countryList)
        addFilter(FilterData("","",0))
    }
    fun filterData(filterData:FilterData){

        var newGlobalData = GlobalData(0,0,0)

        var newList = countryList

        when(filterData.filterType){

            Constant.FILTER_TYPE_GRT->{

                when(filterData.filterField){

                    Constant.SORT_COLUMN_TOTAL_CASES->{
                        newList = countryList?.filter { it.totalConfirmed > filterData.filterValue } as ArrayList<CountryData>
                    }

                    Constant.SORT_COLUMN_DEATHS->{
                        newList= countryList?.filter { it.totalDeaths > filterData.filterValue } as ArrayList<CountryData>
                    }

                    Constant.SORT_COLUMN_RECOVERED->{
                        newList=  countryList?.filter { it.totalRecovered > filterData.filterValue } as ArrayList<CountryData>
                    }

                }

            }
            Constant.FILTER_TYPE_LESS->{

                when(filterData.filterField){

                    Constant.SORT_COLUMN_TOTAL_CASES->{
                        newList =  countryList?.filter { it.totalConfirmed < filterData.filterValue } as ArrayList<CountryData>
                    }

                    Constant.SORT_COLUMN_DEATHS->{
                        newList =  countryList?.filter { it.totalDeaths < filterData.filterValue } as ArrayList<CountryData>
                    }

                    Constant.SORT_COLUMN_RECOVERED->{
                        newList =  countryList?.filter { it.totalRecovered < filterData.filterValue } as ArrayList<CountryData>
                    }

                }

            }

        }

        newList?.forEach {

            newGlobalData.totalConfirmed += it.totalConfirmed
            newGlobalData.totalDeaths += it.totalDeaths
            newGlobalData.totalRecovered += it.totalRecovered

        }

        globaldata.postValue(newGlobalData)
        allCountryWiseList.postValue(newList)

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

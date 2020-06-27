package com.example.carwalecovidtracker.pojo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class CovidResponse(@SerializedName("Global")  val globalData: GlobalData, @SerializedName("Countries")  val countryData: ArrayList<CountryData>, @SerializedName("Date")  val date: String)

data class GlobalData(@SerializedName("TotalConfirmed")  var totalConfirmed: Int
                      , @SerializedName("TotalDeaths")  var totalDeaths: Int
                      , @SerializedName("TotalRecovered")  var totalRecovered: Int)

data class CountryData(@SerializedName("Country")  val country: String, @SerializedName("CountryCode")  val countryCode: String
                       , @SerializedName("Slug")  val slug: String, @SerializedName("NewConfirmed")  val newConfirmed: Int
                       , @SerializedName("TotalConfirmed")  val totalConfirmed: Int, @SerializedName("NewDeaths")  val newDeaths: Int
                       , @SerializedName("TotalDeaths")  val totalDeaths: Int, @SerializedName("NewRecovered")  val newRecoveered: Int
                       , @SerializedName("TotalRecovered")  val totalRecovered: Int, @SerializedName("Date")  val date: String)


data class SortData(val sortType:String, var sortField:String)

data class FilterData(val filterType:String, var filterField:String, var filterValue:Int)
package com.example.carwalecovidtracker.pojo

import com.google.gson.annotations.SerializedName


data class CovidResponse(@SerializedName("Global")  val globalData: GlobalData, @SerializedName("Countries")  val countryData: ArrayList<CountryData>, @SerializedName("Date")  val date: String)

data class GlobalData(@SerializedName("NewConfirmed")  val newConfirmed: Int, @SerializedName("TotalConfirmed")  val totalConfirmed: Int
                      , @SerializedName("NewDeaths")  val newDeaths: String
                      , @SerializedName("TotalDeaths")  val totalDeaths: Int, @SerializedName("NewRecovered")  val newRecoveered: String
                      , @SerializedName("TotalRecovered")  val totalRecovered: Int)

data class CountryData(@SerializedName("Country")  val country: String, @SerializedName("CountryCode")  val countryCode: String
                       , @SerializedName("Slug")  val slug: String, @SerializedName("NewConfirmed")  val newConfirmed: Int
                       , @SerializedName("TotalConfirmed")  val totalConfirmed: Int, @SerializedName("NewDeaths")  val newDeaths: Int
                       , @SerializedName("TotalDeaths")  val totalDeaths: Int, @SerializedName("NewRecovered")  val newRecoveered: Int
                       , @SerializedName("TotalRecovered")  val totalRecovered: Int, @SerializedName("Date")  val date: String)
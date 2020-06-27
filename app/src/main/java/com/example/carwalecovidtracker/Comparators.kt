package com.example.carwalecovidtracker

import com.example.carwalecovidtracker.pojo.CountryData

class Comparators {

    inner class DateCompratorAsc:Comparator<CountryData> {
        override fun compare(country1: CountryData?, country2: CountryData?): Int {
            return country1!!.newConfirmed.compareTo(country2!!.newConfirmed)
        }
    }

    inner class DateCompratorDesc:Comparator<CountryData> {
        override fun compare(country1: CountryData?, country2: CountryData?): Int {
            return country2!!.newConfirmed.compareTo(country1!!.newConfirmed)
        }
    }

    inner class TotalConfirmedAsc:Comparator<CountryData> {
        override fun compare(country1: CountryData?, country2: CountryData?): Int {
            return country1!!.totalConfirmed.compareTo(country2!!.totalConfirmed)
        }
    }

    inner class TotalConfirmedDesc:Comparator<CountryData> {
        override fun compare(country1: CountryData?, country2: CountryData?): Int {
            return country2!!.totalConfirmed.compareTo(country1!!.totalConfirmed)
        }
    }

    inner class TotalDeathsAsc:Comparator<CountryData> {
        override fun compare(country1: CountryData?, country2: CountryData?): Int {
            return country1!!.totalDeaths.compareTo(country2!!.totalDeaths)
        }
    }

    inner class TotalDeathsDesc:Comparator<CountryData> {
        override fun compare(country1: CountryData?, country2: CountryData?): Int {
            return country2!!.totalDeaths.compareTo(country1!!.totalDeaths)
        }
    }

    inner class TotalRecoveredAsc:Comparator<CountryData> {
        override fun compare(country1: CountryData?, country2: CountryData?): Int {
            return country1!!.totalRecovered.compareTo(country2!!.totalRecovered)
        }
    }

    inner class TotalRecoveredDesc:Comparator<CountryData> {
        override fun compare(country1: CountryData?, country2: CountryData?): Int {
            return country2!!.totalRecovered.compareTo(country1!!.totalRecovered)
        }
    }

    inner class CountryAsc:Comparator<CountryData> {
        override fun compare(country1: CountryData?, country2: CountryData?): Int {
            return country1!!.country.compareTo(country2!!.country)
        }
    }

    inner class CountryDesc:Comparator<CountryData> {
        override fun compare(country1: CountryData?, country2: CountryData?): Int {
            return country2!!.country.compareTo(country1!!.country)
        }
    }

}
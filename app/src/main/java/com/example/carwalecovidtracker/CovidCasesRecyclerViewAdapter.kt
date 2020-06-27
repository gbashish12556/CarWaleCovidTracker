package com.example.carwalecovidtracker

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carwalecovidtracker.pojo.CountryData
import java.util.ArrayList

class CovidCasesRecyclerViewAdapter(private val data: ArrayList<CountryData>) : RecyclerView.Adapter<CovidCasesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.covid_cases_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.countryNameTextView.text = data[position].country
        holder.totalCasesTextView.text = data[position].totalConfirmed.toString()
        holder.deathsTextView.text = data[position].totalDeaths.toString()
        holder.recoveredTextView.text = data[position].totalRecovered.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val countryNameTextView: TextView
        val totalCasesTextView: TextView
        val deathsTextView: TextView
        val recoveredTextView: TextView

        init {

            countryNameTextView = itemView.findViewById(R.id.countryName)
            totalCasesTextView = itemView.findViewById(R.id.totalCases)
            deathsTextView = itemView.findViewById(R.id.deaths)
            recoveredTextView = itemView.findViewById(R.id.recovered)
        }
    }

    fun setData(data: List<CountryData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}
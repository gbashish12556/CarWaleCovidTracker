package com.example.carwalecovidtracker

import android.graphics.Typeface
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
        var countryCode = data[position].countryCode

        holder.countryNameTextView.text = data[position].country
        holder.totalCasesTextView.text = data[position].totalConfirmed.toString()
        holder.deathsTextView.text = data[position].totalDeaths.toString()
        holder.recoveredTextView.text = data[position].totalRecovered.toString()

        var userCountry = App.sharedPref.getString(Constant.COUNTRY_NAME,"None")!!.toUpperCase() as CharSequence
        if(userCountry in countryCode.toUpperCase()){
//            Log.d(userCountry.toString(),countryName)
            holder.totalCasesTextView.setTypeface(null, Typeface.BOLD);
            holder.countryNameTextView.setTypeface(null, Typeface.BOLD);
            holder.deathsTextView.setTypeface(null, Typeface.BOLD);
            holder.recoveredTextView.setTypeface(null, Typeface.BOLD);

        }else{
            holder.totalCasesTextView.setTypeface(null, Typeface.NORMAL);
            holder.countryNameTextView.setTypeface(null, Typeface.NORMAL);
            holder.deathsTextView.setTypeface(null, Typeface.NORMAL);
            holder.recoveredTextView.setTypeface(null, Typeface.NORMAL);
        }

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
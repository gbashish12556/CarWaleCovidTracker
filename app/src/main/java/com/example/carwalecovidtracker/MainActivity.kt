package com.example.carwalecovidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.VisibleForTesting
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingResource
import butterknife.BindView
import butterknife.ButterKnife
import com.example.carwalecovidtracker.pojo.CountryData
import com.example.carwalecovidtracker.pojo.FilterData
import com.example.carwalecovidtracker.pojo.SortData
import io.reactivex.subjects.PublishSubject
import java.util.ArrayList



class MainActivity : AppCompatActivity(),CommunicationProvider {


    private var viewModel: ViewModel? = null
    private var allCases: MutableList<CountryData>? = mutableListOf()
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView
    @BindView(R.id.scrollUp) lateinit var scrollUp: ImageView
    @BindView(R.id.scrollDown) lateinit var scrollDown: ImageView
    @BindView(R.id.totalConfirmed) lateinit var totalConfirmed: TextView
    @BindView(R.id.totalDeaths) lateinit var totalDeaths: TextView
    @BindView(R.id.totalRecovered) lateinit var totalRecovered: TextView
    var dialogFragment:DialogFragment? = null
    private var mIdlingResource: RetrofitIdlingResource? = null
    private var sortingPubsub:PublishSubject<SortData> = PublishSubject.create()
    private var filterPubsub:PublishSubject<FilterData> = PublishSubject.create()

    override fun getSortingPubSub(): PublishSubject<SortData> {
       return sortingPubsub
    }

    override fun getFilterPubSub(): PublishSubject<FilterData> {
        return filterPubsub
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getIdlingResource()
        if(mIdlingResource != null) {
            mIdlingResource!!.setIdleState(false)
        }
        ButterKnife.bind(this)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        val countryName = getResources().getConfiguration().locale.getCountry()
        App.sharedPref.edit().putString(Constant.COUNTRY_NAME,countryName).apply()
        initialiseViewModel()

    }



    fun initialiseViewModel(){
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        val noteAdapter = CovidCasesRecyclerViewAdapter(allCases as ArrayList<CountryData>)

        recyclerView!!.adapter = noteAdapter

        viewModel!!.allCountryWiseCases.observe(this, Observer { cases ->
            if (cases.size > 0) {
                if(mIdlingResource != null) {
                    mIdlingResource!!.setIdleState(true)
                }
                noteAdapter.setData(cases)
            }
        })

        viewModel!!.apiStatus.observe(this, Observer {status->
            Toast.makeText(this@MainActivity,"Fetch Failes", Toast.LENGTH_LONG).show()
        })
        viewModel!!.globalData.observe(this, Observer {globaldata->
            totalConfirmed.text = String.format(resources.getString(R.string.total_confirmed,globaldata.totalConfirmed.toString()))
            totalDeaths.text = String.format(resources.getString(R.string.total_deaths,globaldata.totalDeaths.toString()))
            totalRecovered.text = String.format(resources.getString(R.string.total_recovered,globaldata.totalRecovered.toString()))
        })

        sortingPubsub.subscribe{sortData->
            if(mIdlingResource != null) {
                    mIdlingResource!!.setIdleState(false)
            }
            viewModel!!.sortData(sortData)
        }

        filterPubsub.subscribe{filterData->
            if(mIdlingResource != null) {
                    mIdlingResource!!.setIdleState(false)
            }
            viewModel!!.filterData(filterData)
        }

        scrollDown.setOnClickListener{
            recyclerView.smoothScrollBy(0,100)
        }
        scrollUp.setOnClickListener{
            recyclerView.smoothScrollBy(0,-100)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if(dialogFragment != null){
            dialogFragment!!.dismiss()
        }
        when(id){
            R.id.menu_new_content_sort->{
                dialogFragment = SortListFragmeent()
                dialogFragment!!.show(supportFragmentManager,"DialogFragment")
            }
            R.id.menu_new_content_filter->{
                dialogFragment = FilterListFragment()
                dialogFragment!!.show(supportFragmentManager,"DialogFragment")
            }
            R.id.menu_new_content_reset->{
                viewModel!!.resetFilter()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    @VisibleForTesting
    @NonNull
    fun getIdlingResource(): IdlingResource {
        if (mIdlingResource == null) {
            mIdlingResource = RetrofitIdlingResource()
        }
        return mIdlingResource!!
    }

}

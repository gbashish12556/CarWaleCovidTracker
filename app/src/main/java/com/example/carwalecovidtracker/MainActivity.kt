package com.example.carwalecovidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.carwalecovidtracker.pojo.CountryData
import com.example.carwalecovidtracker.pojo.CovidResponse
import com.example.carwalecovidtracker.pojo.FilterData
import com.example.carwalecovidtracker.pojo.SortData
import com.google.gson.Gson
import io.reactivex.subjects.PublishSubject
import java.util.ArrayList

class MainActivity : AppCompatActivity(),CommunicationProvider {


    private var viewModel: ViewModel? = null
    private var allCases: ArrayList<CountryData>? = null
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView
    @BindView(R.id.scrollUp) lateinit var scrollUp: ImageView
    @BindView(R.id.scrollDown) lateinit var scrollDown: ImageView
    @BindView(R.id.totalConfirmed) lateinit var totalConfirmed: TextView
    @BindView(R.id.totalDeaths) lateinit var totalDeaths: TextView
    @BindView(R.id.totalRecovered) lateinit var totalRecovered: TextView
    var dialogFragment:DialogFragment? = null
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
        ButterKnife.bind(this)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        initialiseViewModel()

    }

    fun initialiseViewModel(){
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        allCases = ArrayList()

        var response = "{\"Global\":{\"NewConfirmed\":190531,\"TotalConfirmed\":9557043,\"NewDeaths\":0,\"TotalDeaths\":486079,\"NewRecovered\":398,\"TotalRecovered\":4630138},\"Countries\":[{\"Country\":\"Afghanistan\",\"CountryCode\":\"AF\",\"Slug\":\"afghanistan\",\"NewConfirmed\":694,\"TotalConfirmed\":30175,\"NewDeaths\":0,\"TotalDeaths\":618,\"NewRecovered\":0,\"TotalRecovered\":9260,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Albania\",\"CountryCode\":\"AL\",\"Slug\":\"albania\",\"NewConfirmed\":145,\"TotalConfirmed\":2192,\"NewDeaths\":0,\"TotalDeaths\":45,\"NewRecovered\":0,\"TotalRecovered\":1195,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Algeria\",\"CountryCode\":\"DZ\",\"Slug\":\"algeria\",\"NewConfirmed\":172,\"TotalConfirmed\":12248,\"NewDeaths\":0,\"TotalDeaths\":861,\"NewRecovered\":0,\"TotalRecovered\":8674,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Andorra\",\"CountryCode\":\"AD\",\"Slug\":\"andorra\",\"NewConfirmed\":0,\"TotalConfirmed\":855,\"NewDeaths\":0,\"TotalDeaths\":52,\"NewRecovered\":0,\"TotalRecovered\":797,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Angola\",\"CountryCode\":\"AO\",\"Slug\":\"angola\",\"NewConfirmed\":8,\"TotalConfirmed\":197,\"NewDeaths\":0,\"TotalDeaths\":10,\"NewRecovered\":0,\"TotalRecovered\":77,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Antigua and Barbuda\",\"CountryCode\":\"AG\",\"Slug\":\"antigua-and-barbuda\",\"NewConfirmed\":39,\"TotalConfirmed\":65,\"NewDeaths\":0,\"TotalDeaths\":3,\"NewRecovered\":0,\"TotalRecovered\":22,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Argentina\",\"CountryCode\":\"AR\",\"Slug\":\"argentina\",\"NewConfirmed\":2648,\"TotalConfirmed\":49851,\"NewDeaths\":0,\"TotalDeaths\":1078,\"NewRecovered\":0,\"TotalRecovered\":13576,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Armenia\",\"CountryCode\":\"AM\",\"Slug\":\"armenia\",\"NewConfirmed\":1482,\"TotalConfirmed\":22488,\"NewDeaths\":0,\"TotalDeaths\":372,\"NewRecovered\":0,\"TotalRecovered\":10144,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Australia\",\"CountryCode\":\"AU\",\"Slug\":\"australia\",\"NewConfirmed\":37,\"TotalConfirmed\":7558,\"NewDeaths\":0,\"TotalDeaths\":103,\"NewRecovered\":0,\"TotalRecovered\":6924,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Austria\",\"CountryCode\":\"AT\",\"Slug\":\"austria\",\"NewConfirmed\":69,\"TotalConfirmed\":17477,\"NewDeaths\":0,\"TotalDeaths\":693,\"NewRecovered\":0,\"TotalRecovered\":16261,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Azerbaijan\",\"CountryCode\":\"AZ\",\"Slug\":\"azerbaijan\",\"NewConfirmed\":590,\"TotalConfirmed\":14305,\"NewDeaths\":0,\"TotalDeaths\":167,\"NewRecovered\":0,\"TotalRecovered\":7503,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Bahamas\",\"CountryCode\":\"BS\",\"Slug\":\"bahamas\",\"NewConfirmed\":0,\"TotalConfirmed\":104,\"NewDeaths\":0,\"TotalDeaths\":11,\"NewRecovered\":0,\"TotalRecovered\":83,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Bahrain\",\"CountryCode\":\"BH\",\"Slug\":\"bahrain\",\"NewConfirmed\":508,\"TotalConfirmed\":23570,\"NewDeaths\":0,\"TotalDeaths\":67,\"NewRecovered\":0,\"TotalRecovered\":17450,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Bangladesh\",\"CountryCode\":\"BD\",\"Slug\":\"bangladesh\",\"NewConfirmed\":7408,\"TotalConfirmed\":126606,\"NewDeaths\":0,\"TotalDeaths\":1545,\"NewRecovered\":0,\"TotalRecovered\":47635,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Barbados\",\"CountryCode\":\"BB\",\"Slug\":\"barbados\",\"NewConfirmed\":0,\"TotalConfirmed\":97,\"NewDeaths\":0,\"TotalDeaths\":7,\"NewRecovered\":0,\"TotalRecovered\":85,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Belarus\",\"CountryCode\":\"BY\",\"Slug\":\"belarus\",\"NewConfirmed\":895,\"TotalConfirmed\":60382,\"NewDeaths\":0,\"TotalDeaths\":357,\"NewRecovered\":0,\"TotalRecovered\":38688,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Belgium\",\"CountryCode\":\"BE\",\"Slug\":\"belgium\",\"NewConfirmed\":197,\"TotalConfirmed\":61007,\"NewDeaths\":0,\"TotalDeaths\":9713,\"NewRecovered\":0,\"TotalRecovered\":16771,\"Date\":\"2020-06-25T19:46:08Z\"},{\"Country\":\"Zimbabwe\",\"CountryCode\":\"ZW\",\"Slug\":\"zimbabwe\",\"NewConfirmed\":5,\"TotalConfirmed\":530,\"NewDeaths\":0,\"TotalDeaths\":6,\"NewRecovered\":0,\"TotalRecovered\":64,\"Date\":\"2020-06-25T19:46:08Z\"}],\"Date\":\"2020-06-25T19:46:08Z\"}"

        var covidResponse = Gson().fromJson<CovidResponse>(response,CovidResponse::class.java)

        val noteAdapter = CovidCasesRecyclerViewAdapter(covidResponse.countryData!!)

        recyclerView!!.adapter = noteAdapter

        viewModel!!.allCountryWiseCases.observe(this, Observer { cases ->
            if (cases.size > 0) {
                noteAdapter.setData(cases)
            }
        })

        viewModel!!.globalData.observe(this, Observer {globaldata->
            totalConfirmed.text = String.format(resources.getString(R.string.total_confirmed,globaldata.totalConfirmed.toString()))
            totalDeaths.text = String.format(resources.getString(R.string.total_deaths,globaldata.totalDeaths.toString()))
            totalRecovered.text = String.format(resources.getString(R.string.total_recovered,globaldata.totalRecovered.toString()))
        })

        sortingPubsub.subscribe{sortData->
            viewModel!!.sortData(sortData)
        }
        filterPubsub.subscribe{filterData->
            addFilter(filterData)
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
                addFilter(FilterData("","",0))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun addFilter(filterData:FilterData){

        val editor = App.sharedPref.edit()
        editor.putString(Constant.FILTER_RANGE_TYPE, filterData.filterType);
        editor.putString(Constant.FILTER_FIELD, filterData.filterField);
        editor.putInt(Constant.FILTER_VALUE, filterData.filterValue);
        editor.apply()

    }
}

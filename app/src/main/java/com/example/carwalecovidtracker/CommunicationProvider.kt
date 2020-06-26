package com.example.carwalecovidtracker

import com.example.carwalecovidtracker.pojo.FilterData
import com.example.carwalecovidtracker.pojo.SortData
import io.reactivex.subjects.PublishSubject

interface CommunicationProvider {

    fun getSortingPubSub(): PublishSubject<SortData>
    fun getFilterPubSub(): PublishSubject<FilterData>

}
package com.cts.canada.presenter

import android.content.Context
import au.com.suncorp.marketplace.application.rx.SingleSubscriber
import com.cts.canada.model.Facts
import com.cts.canada.model.FactsRowItem
import com.cts.canada.network.RetrofitClientInstance
import com.cts.canada.util.FileParsing
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FactsPresenter(val context: Context) {

    private lateinit var display: Display

    private val getFactsSubscriber =
        SingleSubscriber(this::onGetPayIdsSuccess, this::onGetPayIdsFailure)

    private val retrofitClientInstance by lazy {
        RetrofitClientInstance.getClient(context)
    }

    fun onResume()  = getFactsData()

    fun onPause() = getFactsSubscriber.clear()

    fun onStop() = getFactsSubscriber.clear()

    fun getFactsData() {
        if (context is Display) {
            display = context
        } else {
            throw ClassCastException(
                context.toString() + " must implement OnDogSelected.")
        }

        getFactsSubscriber.dispatch(
            retrofitClientInstance.getFacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {

                }
                .doAfterTerminate {

                })

        getFactsSubscriber.subscribe()
    }


    fun onGetPayIdsSuccess(facts: Facts) {
        display.displayFactsList(facts.rows)
    }

    fun onGetPayIdsFailure(throwable: Throwable) {
        val parsedData = FileParsing.parseJson(context)
        display.showError(throwable)
        display.setDummyFactsData(parsedData.rows)
    }

    interface Display {

        fun displayFactsList(factsData: ArrayList<FactsRowItem>)

        fun showError(throwable : Throwable)

        fun setDummyFactsData(factsData: ArrayList<FactsRowItem>)
    }
}
package com.cts.canada.presenter

import android.app.Application
import android.content.Context
import au.com.suncorp.marketplace.application.rx.SingleSubscriber
import com.cts.canada.dependencyinjection.module.RxThreadModule
import com.cts.canada.model.Facts
import com.cts.canada.model.FactsRowItem
import com.cts.canada.network.RetrofitClientInstance
import javax.inject.Inject
import io.reactivex.Scheduler
import javax.inject.Named

class FactsPresenter @Inject constructor(@Named(RxThreadModule.mainThread) private val mainSchedulers: Scheduler,
                                         @Named(RxThreadModule.ioThread) private val ioScheduler: Scheduler) {

    public lateinit var display: Display
    public lateinit var context: Context

    private val getFactsSubscriber =
        SingleSubscriber(this::onGetPayIdsSuccess, this::onGetPayIdsFailure)

    private val retrofitClientInstance by lazy {
        RetrofitClientInstance.getClient(context)
    }

    fun inject(display: Display) {
        this.display = display
    }

    fun appContext(context: Context) {
        this.context = context
    }

    fun onResume() {
       // getDisplayContext(context)
       // getFactsData()
        display.checkDagger()
    }

    fun onPause() = getFactsSubscriber.dispose()

    fun onStop() = getFactsSubscriber.clear()

    fun getFactsData() {
        getFactsSubscriber.dispatch(
            retrofitClientInstance.getFacts()
                .subscribeOn(ioScheduler)
                .observeOn(mainSchedulers)
                .doOnSubscribe {

                }
                .doAfterTerminate {

                })

        getFactsSubscriber.subscribe()
    }

//    fun getDisplayContext(context:Context): Display {
//        if (context is Display) {
//            display = context
//        } else {
//            throw ClassCastException(
//                context.toString() + " must implement methods."
//            )
//        }
//        return display
//    }

    fun onGetPayIdsSuccess(facts: Facts) {
        display.displayFactsList(facts.rows)
    }

    fun onGetPayIdsFailure(throwable: Throwable) {
      //  val parsedData = FileParsing.parseJson(context)
        display.showError(throwable)
      //  display.setDummyFactsData(parsedData.rows)
    }

    interface Display {

        fun displayFactsList(factsData: ArrayList<FactsRowItem>)

        fun showError(throwable: Throwable)

        fun setDummyFactsData(factsData: ArrayList<FactsRowItem>)

        fun checkDagger()
    }
}
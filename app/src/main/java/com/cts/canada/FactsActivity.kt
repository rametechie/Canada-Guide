package com.cts.canada

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cts.canada.adapter.FactsAdapter
import com.cts.canada.model.FactsRowItem
import com.cts.canada.network.RetrofitClientInstance
import com.cts.canada.presenter.FactsPresenter

class FactsActivity : AppCompatActivity() , FactsPresenter.Display {


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var factsPresenter: FactsPresenter
    //private var disposable: Disposable? = null
//    private val getFactsSubscriber =
//        SingleSubscriber(this::onGetPayIdsSuccess, this::onGetPayIdsFailure)


    protected val factsAdapter: FactsAdapter by lazy {
        FactsAdapter(this)
    }
    private val retrofitClientInstance by lazy {
        RetrofitClientInstance.getClient(this.applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        factsPresenter =  FactsPresenter(this)
//        factsPresenter.getFactsData()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = factsAdapter
    }

    override fun onResume() {
        super.onResume()
        factsPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
//        disposable?.dispose()
        //getFactsSubscriber.dispose()
        factsPresenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        //getFactsSubscriber.clear()
        factsPresenter.onStop()
    }


//    private fun getFactsData() {
//
////        disposable = retrofitClientInstance.getFacts()
////            .subscribeOn(Schedulers.io())
////            .observeOn(AndroidSchedulers.mainThread())
////            .subscribe(
////                { result ->   factsAdapter.setFactsList(result.rows)},
////                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
////            )
//
//        getFactsSubscriber.dispatch(
//            retrofitClientInstance.getFacts()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe {
//
//                }
//                .doAfterTerminate {
//
//                })
//
//        getFactsSubscriber.subscribe()
//    }

//
//    private fun onGetPayIdsSuccess(facts: Facts) {
//        factsAdapter.setFactsList(facts.rows)
////        val parsedData = FileParsing.parseJson(this)
////        factsAdapter.setFactsList(parsedData.rows)
//    }
//
//    private fun onGetPayIdsFailure(throwable: Throwable) {
//        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
//        val parsedData = FileParsing.parseJson(this)
//        factsAdapter.setFactsList(parsedData.rows)
//    }


    override fun displayFactsList(factsData: ArrayList<FactsRowItem>) {
                factsAdapter.setFactsList(factsData)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun setDummyFactsData(factsListData: ArrayList<FactsRowItem>) {
        factsAdapter.setFactsList(factsListData)
    }

}




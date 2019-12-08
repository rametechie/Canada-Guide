package com.cts.canada

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.com.suncorp.marketplace.application.rx.SingleSubscriber
import com.cts.canada.adapter.FactsAdapter
import com.cts.canada.model.Facts
import com.cts.canada.model.FactsRowItem
import com.cts.canada.network.RetrofitClientInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    //private var disposable: Disposable? = null
    private val getFactsSubscriber = SingleSubscriber(this::onGetPayIdsSuccess, this::onGetPayIdsFailure)


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
        getFactsData()
         recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
             recyclerView.layoutManager = viewManager
             recyclerView.adapter = factsAdapter
    }

    override fun onPause() {
        super.onPause()
//        disposable?.dispose()
        getFactsSubscriber.dispose()
    }

    override fun onStop() {
        super.onStop()
        getFactsSubscriber.clear()
    }


    private fun getFactsData() {

//        disposable = retrofitClientInstance.getFacts()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->   factsAdapter.setFactsList(result.rows)},
//                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
//            )

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



    private fun onGetPayIdsSuccess(facts: Facts) {
        factsAdapter.setFactsList(facts.rows)
    }

    private fun onGetPayIdsFailure(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
   }




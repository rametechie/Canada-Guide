package com.cts.canada

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cts.canada.adapter.FactsAdapter
import com.cts.canada.model.Facts
import com.cts.canada.model.FactsRowItem
import com.cts.canada.network.RetrofitClientInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var disposable: Disposable? = null
    private var resultData: Facts? = null


    private val retrofitClientInstance by lazy {
        RetrofitClientInstance.getClient(this.applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        getFactsData()
        var dummyListData: ArrayList<FactsRowItem> =
            arrayListOf(FactsRowItem("ramesh", "just for checking the recyclerview to find if the views are coming properly", "imageview"))

        if (resultData != null) {
            viewAdapter = FactsAdapter(resultData!!.rowsList, this)
        } else {
            viewAdapter = FactsAdapter(dummyListData, this)
        }

         recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }


    private fun getFactsData() {

        disposable = retrofitClientInstance.getFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->   viewAdapter = FactsAdapter(resultData!!.rowsList, this) },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )


    }
   }




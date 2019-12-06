package com.cts.canada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cts.canada.adapter.FactsAdapter
import com.cts.canada.model.Facts
import com.cts.canada.model.FactsRowItem
import com.cts.canada.network.ApiService
import com.cts.canada.network.RetrofitClientInstance
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import rx.Scheduler
import rx.Subscriber
import java.util.*
import kotlin.collections.ArrayList
import io.reactivex.Single

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        viewManager = LinearLayoutManager(this)
        getFactsData()
        var dummyListData: ArrayList<FactsRowItem> =
            arrayListOf(FactsRowItem("ramesh", "just for checking the recyclerview", "imageview"))

        viewAdapter = FactsAdapter(dummyListData, this)
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


    private fun getFactsData() {
        val apiService: ApiService =
            RetrofitClientInstance.getClient(this.applicationContext)!!.create(ApiService::class.java)
            apiService.getFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                mapDataToDomainPayIds(response)
            }

    }

   }


    fun mapDataToDomainPayIds(responce:Facts)
    {
        println("factsstring" + "$responce" + responce.rowsList)
    }


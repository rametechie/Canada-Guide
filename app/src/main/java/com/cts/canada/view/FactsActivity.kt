package com.cts.canada.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cts.canada.MainApplication
import com.cts.canada.R
import com.cts.canada.adapter.FactsAdapter
import com.cts.canada.model.FactsRowItem
import com.cts.canada.presenter.FactsPresenter
import javax.inject.Inject

class FactsActivity : AppCompatActivity() , FactsPresenter.Display {


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var factsPresenter: FactsPresenter

    @Inject
    lateinit var presenter: FactsPresenter

    fun inject() {
//        activityComponent.inject(this)
//        presenter.inject(this)

        (application as MainApplication).component.inject(this)


        presenter.inject(this)

        presenter.appContext(this)
    }


    protected val factsAdapter: FactsAdapter by lazy {
        FactsAdapter(this)
    }

//    private val retrofitClientInstance by lazy {
//        RetrofitClientInstance.getClient(this.applicationContext)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val activityComponent = DaggerActivityComponent.builder()
//            .applicationComponent(FactsApplication.applicationComponent)
//            .build()
//        val activityComponent = DaggerActivityComponent.builder()
//                      .build()
        inject()


        viewManager = LinearLayoutManager(this)
//        factsPresenter =  FactsPresenter()
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
        factsPresenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        factsPresenter.onStop()
    }

    override fun displayFactsList(factsData: ArrayList<FactsRowItem>) {
                factsAdapter.setFactsList(factsData)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun setDummyFactsData(factsListData: ArrayList<FactsRowItem>) {
        factsAdapter.setFactsList(factsListData)
    }

    override fun checkDagger() {
        Toast.makeText(this, "Dagger is working", Toast.LENGTH_LONG).show()
    }

}




package com.cts.canada.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ramesh.P on 12/05/19.
 */
class RetrofitClientInstance {

    companion object
    {
        private val BASE_URL = "https://dl.dropboxusercontent.com"
        fun getClient(context: Context):ApiService {

                val retrofit  = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(ApiService::class.java)

        }



    }





}
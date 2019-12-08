package com.cts.canada.network

import android.content.Context
import com.cts.canada.model.Facts
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("/s/2iodh4vg0eortkl/facts.js.")
    fun getFacts(): Single<Facts>


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
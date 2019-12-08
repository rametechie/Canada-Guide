package com.cts.canada.network

import android.content.Context
import com.amit.saha.util.Constant
import com.cts.canada.model.Facts
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET(Constant.FACT_INFO)
    fun getFacts(): Single<Facts>
}
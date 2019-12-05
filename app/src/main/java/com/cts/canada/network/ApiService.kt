package com.cts.canada.network

import com.cts.canada.model.Facts
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/s/2iodh4vg0eortkl/facts.js.")
    fun getFacts(): Single<Facts>
}
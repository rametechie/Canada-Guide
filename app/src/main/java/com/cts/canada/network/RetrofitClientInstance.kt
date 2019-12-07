package com.cts.canada.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor;
import java.util.concurrent.TimeUnit

/**
 * Created by Ramesh.P on 12/05/19.
 */
class RetrofitClientInstance {
    val logging = HttpLoggingInterceptor()


    companion object
    {
        private val BASE_URL = "https://dl.dropboxusercontent.com"
        fun getClient(context: Context):ApiService {
                val retrofit  = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(provideOkhttpClient())
                    .build()
            return retrofit.create(ApiService::class.java)
        }

        fun provideOkhttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor ()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            return httpClient.build()
        }
    }



}
package com.jeancorzo.rickandmorty.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceGenerator : ServiceGenerator {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit: Retrofit by lazy {
        val httpClient = OkHttpClient.Builder().build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    override fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

}
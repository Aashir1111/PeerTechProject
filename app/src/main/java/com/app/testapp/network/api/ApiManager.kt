package com.app.testapp.network.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {

     val baseUrl = "https://api.github.com/"
    private var httpClient: OkHttpClient = OkHttpClient()
    private lateinit var builder: OkHttpClient.Builder

    fun create(): ApiCalls {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder = setupHttpClient(logging)
        return buildRetrofit(builder)
    }

    private fun setupHttpClient(logging: HttpLoggingInterceptor): OkHttpClient.Builder {
        with(httpClient.newBuilder()) {
            return this
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(100, 5, TimeUnit.SECONDS))
                .retryOnConnectionFailure(true)
                .addInterceptor(logging)
        }
    }

    private fun buildRetrofit(builder: OkHttpClient.Builder): ApiCalls {
        val gson = GsonBuilder()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .validateEagerly(true)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson.create()))
            .client(builder.build())
            .build()
        return retrofit.create(ApiCalls::class.java)
    }
}
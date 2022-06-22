package com.github.taasonei.network

import com.github.taasonei.model.FoxPhoto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomfox.ca/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FoxApiService {
    @GET("floof")
    suspend fun getPhoto(): FoxPhoto
}

object FoxApi {
    val retrofitService: FoxApiService by lazy {
        retrofit.create(FoxApiService::class.java)
    }
}
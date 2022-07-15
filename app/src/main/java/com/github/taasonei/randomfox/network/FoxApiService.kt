package com.github.taasonei.randomfox.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomfox.ca/"
private const val URL_PATH = "floof"

//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(MoshiService.moshi))
    .baseUrl(BASE_URL)
    .build()

interface FoxApiService {
    @GET(URL_PATH)
    suspend fun getPhoto(): ResponseFox
}

object FoxApi {
    val retrofitService: FoxApiService by lazy {
        retrofit.create(FoxApiService::class.java)
    }
}
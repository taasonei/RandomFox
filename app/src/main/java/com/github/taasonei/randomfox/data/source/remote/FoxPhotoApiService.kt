package com.github.taasonei.randomfox.data.source.remote

import com.github.taasonei.randomfox.data.source.model.remote.NetworkFoxPhoto
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://randomfox.ca/"
private const val URL_PATH = "floof"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .build()


interface FoxPhotoApiService {
    @GET(URL_PATH)
    suspend fun getFoxPhoto(): NetworkFoxPhoto
}

object FoxPhotoApi {
    val retrofitService: FoxPhotoApiService by lazy {
        retrofit.create(FoxPhotoApiService::class.java)
    }
}
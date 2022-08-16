package com.github.taasonei.randomfox.data.network

import com.github.taasonei.randomfox.ui.model.FoxPhoto
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiService {
    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val jsonAdapter: JsonAdapter<FoxPhoto> = moshi.adapter(FoxPhoto::class.java)

    fun foxPhotoToJson(foxPhoto: FoxPhoto): String {
        return jsonAdapter.toJson(foxPhoto)
    }

    fun foxPhotoFromJson(json: String): FoxPhoto {
        return jsonAdapter.fromJson(json) ?: FoxPhoto("", "")
    }
}
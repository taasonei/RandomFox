package com.github.taasonei.randomfox.data.source

import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiService {
    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val jsonAdapter: JsonAdapter<DomainFoxPhoto> = moshi.adapter(DomainFoxPhoto::class.java)

    fun foxPhotoToJson(domainFoxPhoto: DomainFoxPhoto): String {
        return jsonAdapter.toJson(domainFoxPhoto)
    }

    fun foxPhotoFromJson(json: String): DomainFoxPhoto {
        return jsonAdapter.fromJson(json) ?: DomainFoxPhoto(image = "", link = "")
    }
}
package com.github.taasonei.randomfox.network

import com.github.taasonei.randomfox.model.FoxPhoto
import com.squareup.moshi.Json

data class ResponseFox(
    @Json(name = "image") val image: String?,
    @Json(name = "link") val link: String?
)

fun ResponseFox.asFoxPhoto(): FoxPhoto {
    return FoxPhoto(
        image = this.image ?: "",
        link = this.link ?: "",
        isFavourite = false
    )
}
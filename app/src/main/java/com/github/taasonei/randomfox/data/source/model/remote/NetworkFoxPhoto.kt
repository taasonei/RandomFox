package com.github.taasonei.randomfox.data.source.model.remote

import com.github.taasonei.randomfox.domain.model.FoxPhoto
import com.squareup.moshi.Json

data class NetworkFoxPhoto(
    @Json(name = "image") val image: String?,
    @Json(name = "link") val link: String?
)

fun NetworkFoxPhoto.asFoxPhoto(): FoxPhoto {
    return FoxPhoto(
        image = this.image ?: "",
        link = this.link ?: ""
    )
}

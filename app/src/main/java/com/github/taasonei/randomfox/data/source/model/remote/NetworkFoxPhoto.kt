package com.github.taasonei.randomfox.data.source.model.remote

import com.github.taasonei.randomfox.domain.model.FoxPhoto
import com.squareup.moshi.Json

data class NetworkFoxPhoto(
    @Json(name = "image") val image: String?,
    @Json(name = "link") val link: String?
)


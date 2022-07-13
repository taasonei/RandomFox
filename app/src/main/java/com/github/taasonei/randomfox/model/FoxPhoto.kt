package com.github.taasonei.randomfox.model

import androidx.annotation.DrawableRes
import com.github.taasonei.randomfox.R

data class FoxPhoto(
    val link: String,
    val image: String,
    var isFavourite: Boolean = false
)
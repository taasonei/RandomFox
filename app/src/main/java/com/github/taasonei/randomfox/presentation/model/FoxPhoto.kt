package com.github.taasonei.randomfox.presentation.model

data class FoxPhoto(
    val link: String,
    val image: String,
    var id: Long? = null,
    var isFavourite: Boolean = false
)

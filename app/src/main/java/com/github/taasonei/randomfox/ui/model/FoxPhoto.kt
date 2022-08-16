package com.github.taasonei.randomfox.ui.model

data class FoxPhoto(
    val link: String,
    val image: String,
    var id: Long? = null,
    var isFavourite: Boolean = false
)
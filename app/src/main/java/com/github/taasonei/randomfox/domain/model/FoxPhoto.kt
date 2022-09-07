package com.github.taasonei.randomfox.domain.model

data class FoxPhoto(
    val id: Long? = null,
    val image: String,
    val link: String,
    val isFavourite: Boolean = false
)
package com.github.taasonei.randomfox.domain.model

data class DomainFoxPhoto(
    var id: Long? = null,
    val image: String,
    val link: String,
    var isFavourite: Boolean = false
)

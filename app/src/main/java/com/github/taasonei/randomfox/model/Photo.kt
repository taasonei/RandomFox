package com.github.taasonei.randomfox.model

import androidx.annotation.DrawableRes
import com.github.taasonei.randomfox.R

data class FoxPhoto(
    val link: String,
    val image: String,
    var isFavourite: Boolean = false
)

data class Photo(
    val id: String,
    @DrawableRes val res: Int
)

val photoList = listOf<Photo>(
    Photo("1", R.drawable.placeholder_fox),
    Photo("2", R.drawable.placeholder_fox),
    Photo("3", R.drawable.placeholder_fox),
    Photo("4", R.drawable.placeholder_fox),
    Photo("5", R.drawable.placeholder_fox),
    Photo("6", R.drawable.placeholder_fox),
    Photo("7", R.drawable.placeholder_fox),
    Photo("8", R.drawable.placeholder_fox),
    Photo("9", R.drawable.placeholder_fox),
    Photo("10", R.drawable.placeholder_fox),
)
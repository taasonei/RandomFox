package com.github.taasonei.randomfox.data.source.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "last")
data class LastFoxPhoto(
    @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "is_favourite") var isFavourite: Boolean = false
)

package com.github.taasonei.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteFoxPhoto(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "image_link") val imageLink: String
)

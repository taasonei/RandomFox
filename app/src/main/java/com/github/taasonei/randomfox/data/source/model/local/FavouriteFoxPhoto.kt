package com.github.taasonei.randomfox.data.source.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.taasonei.randomfox.domain.model.FoxPhoto

@Entity(tableName = "favourites")
data class FavouriteFoxPhoto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "link") val link: String
)

fun List<FavouriteFoxPhoto>.asListFoxPhoto(): List<FoxPhoto> {
    return map { favouriteFoxPhoto ->
        FoxPhoto(
            id = favouriteFoxPhoto.id,
            image = favouriteFoxPhoto.image,
            link = favouriteFoxPhoto.link,
            isFavourite = true
        )
    }
}
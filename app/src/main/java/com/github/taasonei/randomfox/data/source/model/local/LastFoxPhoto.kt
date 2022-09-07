package com.github.taasonei.randomfox.data.source.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.github.taasonei.randomfox.domain.model.FoxPhoto

@Entity(tableName = "last")
data class LastFoxPhoto(
    @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "is_favourite") val isFavourite: Boolean = false
)

fun LastFoxPhoto.asFoxPhoto(): FoxPhoto {
    return FoxPhoto(
        id = this.id,
        image = this.image,
        link = this.link,
        isFavourite = this.isFavourite
    )
}

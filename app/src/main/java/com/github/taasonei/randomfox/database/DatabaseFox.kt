package com.github.taasonei.randomfox.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.taasonei.randomfox.model.FoxPhoto

@Entity(tableName = "favourites")
data class DatabaseFox(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "image") val image: String
)

fun List<DatabaseFox>.asFoxPhotoList(): List<FoxPhoto> {
    return map { dbFox ->
        FoxPhoto(
            id = dbFox.id,
            link = dbFox.link,
            image = dbFox.image,
            isFavourite = true
        )
    }
}

package com.github.taasonei.randomfox.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.taasonei.randomfox.model.FoxPhoto

@Entity(tableName = "favourites")
data class DatabaseFox(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "image_link") val imageLink: String
)

fun List<DatabaseFox>.asFoxPhotoList(): List<FoxPhoto> {
    return map { dbFox ->
        FoxPhoto(
            link = dbFox.id,
            image = dbFox.imageLink,
            isFavourite = true
        )
    }
}

fun DatabaseFox.asFoxPhoto(): FoxPhoto {
    return FoxPhoto(
        image = this.imageLink,
        link = this.id,
        isFavourite = true
    )
}

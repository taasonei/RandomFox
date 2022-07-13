package com.github.taasonei.randomfox.repository

import android.content.Context
import com.github.taasonei.randomfox.data.dataStore
import com.github.taasonei.randomfox.database.DatabaseFox
import com.github.taasonei.randomfox.database.FoxDatabase
import com.github.taasonei.randomfox.database.asFoxPhotoList
import com.github.taasonei.randomfox.model.FoxPhoto
import com.github.taasonei.randomfox.network.FoxApi
import com.github.taasonei.randomfox.network.asFoxPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class FoxRepository(private val context: Context) {

    private val database: FoxDatabase = FoxDatabase.getDatabase(context)

    private suspend fun fetchLastFox() = context.dataStore.data.first()

    suspend fun updateLastFox(foxPhoto: FoxPhoto) {
        context.dataStore.updateData { currentLastFox ->
            currentLastFox.toBuilder()
                .setImage(foxPhoto.image)
                .setLink(foxPhoto.link)
                .setIsFavourite(foxPhoto.isFavourite)
                .build()
        }
    }

    suspend fun firstLoadFoxPhoto(): FoxPhoto {
        val lastFox = fetchLastFox()
        return if (lastFox.image.isNotBlank() && lastFox.link.isNotBlank()) {
            FoxPhoto(
                link = lastFox.link,
                image = lastFox.image,
                isFavourite = lastFox.isFavourite
            )
        } else {
            loadFoxPhoto()
        }
    }

    suspend fun loadFoxPhoto(): FoxPhoto {
        val response = FoxApi.retrofitService.getPhoto()

        if (response.image != null && response.link != null) {
            val foxPhoto = response.asFoxPhoto()
            val dbFox = database.favouritesDao().getFoxPhoto(response.link)

            if (dbFox != null) {
                foxPhoto.isFavourite = true
            }

            updateLastFox(foxPhoto)

            return foxPhoto
        } else {
            throw IllegalArgumentException("Get null property when waiting for string")
        }
    }


    suspend fun insert(dbFox: DatabaseFox) {
        database.favouritesDao().insertFoxPhoto(dbFox)
    }

    suspend fun delete(dbFox: DatabaseFox) {
        database.favouritesDao().deleteFoxPhoto(dbFox)
    }

    fun getFoxPhotosFromDatabase(): Flow<List<FoxPhoto>> {
        return database.favouritesDao().getAllFoxPhotos().map { listDbFox ->
            listDbFox.asFoxPhotoList()
        }
    }

}
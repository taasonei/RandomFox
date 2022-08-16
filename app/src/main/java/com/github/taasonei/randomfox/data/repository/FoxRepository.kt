package com.github.taasonei.randomfox.data.repository

import android.content.Context
import android.util.Log
import com.github.taasonei.randomfox.data.LastFox
import com.github.taasonei.randomfox.data.database.DatabaseFox
import com.github.taasonei.randomfox.data.database.FoxDatabase
import com.github.taasonei.randomfox.data.database.asFoxPhotoList
import com.github.taasonei.randomfox.ui.model.FoxPhoto
import com.github.taasonei.randomfox.data.network.FoxApi
import com.github.taasonei.randomfox.data.network.asFoxPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FoxRepository(context: Context) {

    private val database: FoxDatabase = FoxDatabase.getDatabase(context)

    private val lastFox = LastFox(context)

    fun writeData(foxPhoto: FoxPhoto) {
        try {
            lastFox.writeFile(foxPhoto)
        } catch (e: AccessDeniedException) {
            Log.d("tag", e.stackTraceToString())
        }
    }

    suspend fun firstLoadFoxPhoto(): FoxPhoto {
        return try {
            val foxFromFile = lastFox.readFile()
            if (foxFromFile.link.isNotBlank() && foxFromFile.image.isNotBlank()) {
                foxFromFile
            } else {
                loadFoxPhoto()
            }
        } catch (e: Exception){
            Log.d("tag", e.stackTraceToString())
            loadFoxPhoto()
        }
    }

    suspend fun loadFoxPhoto(): FoxPhoto {
        val response = FoxApi.retrofitService.getPhoto()

        if (response.image != null && response.link != null) {
            val foxPhoto = response.asFoxPhoto()
            val dbFox = database.favouritesDao().getFoxPhoto(response.link)

            if (dbFox != null) {
                foxPhoto.id = dbFox.id
                foxPhoto.isFavourite = true
            }

            writeData(foxPhoto)

            return foxPhoto
        } else {
            throw IllegalArgumentException("Get null property when waiting for string")
        }
    }


    suspend fun insert(dbFox: DatabaseFox): Long {
        return database.favouritesDao().insertFoxPhoto(dbFox)
    }

    suspend fun delete(dbFox: DatabaseFox) {
        database.favouritesDao().deleteFoxPhoto(dbFox)
    }

    fun getFoxPhotosFromDatabase(): Flow<List<FoxPhoto>> {
        return database.favouritesDao().getAllFoxPhotos().map { listDbFox ->
            listDbFox.asFoxPhotoList()
        }
    }

    suspend fun getFoxPhotoId(rowId: Long): Long {
        return database.favouritesDao().getFoxPhotoId(rowId)
    }

}
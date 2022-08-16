package com.github.taasonei.randomfox.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM favourites ORDER BY id")
    fun getAllFoxPhotos(): Flow<List<DatabaseFox>>

    @Query("SELECT * FROM favourites WHERE link = :link")
    suspend fun getFoxPhoto(link: String): DatabaseFox?

    @Query("SELECT id FROM favourites WHERE rowid = :rowId")
    suspend fun getFoxPhotoId(rowId: Long): Long

    @Delete
    suspend fun deleteFoxPhoto(databaseFox: DatabaseFox)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoxPhoto(databaseFox: DatabaseFox): Long

}
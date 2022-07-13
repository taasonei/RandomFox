package com.github.taasonei.randomfox.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM favourites")
    fun getAllFoxPhotos(): Flow<List<DatabaseFox>>

    @Query("SELECT * FROM favourites WHERE id = :id")
    suspend fun getFoxPhoto(id: String): DatabaseFox?

    @Delete
    suspend fun deleteFoxPhoto(databaseFox: DatabaseFox)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoxPhoto(databaseFox: DatabaseFox)

}
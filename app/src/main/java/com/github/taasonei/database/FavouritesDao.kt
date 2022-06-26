package com.github.taasonei.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM favourites")
    fun getAllPhotos(): Flow<List<FavouriteFoxPhoto>>

    @Query("SELECT * FROM favourites WHERE id = :id")
    fun getPhoto(id: String): Flow<FavouriteFoxPhoto>

    @Delete
    suspend fun deletePhoto(foxPhoto: FavouriteFoxPhoto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(foxPhoto: FavouriteFoxPhoto)

}
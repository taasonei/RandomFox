package com.github.taasonei.randomfox.data.source.local

import androidx.room.*
import com.github.taasonei.randomfox.data.source.model.local.FavouriteFoxPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesFoxPhotoDao {

    @Query("SELECT * FROM favourites ORDER BY id")
    fun getFavourites(): Flow<List<FavouriteFoxPhoto>>

    @Query("SELECT * FROM favourites WHERE link = :link")
    fun getPhotoByLink(link: String): FavouriteFoxPhoto

    @Query("SELECT id FROM favourites WHERE rowid = :rowId")
    fun getPhotoIdByRowId(rowId: Long): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favouriteFoxPhoto: FavouriteFoxPhoto): Long

    @Delete
    fun delete(favouriteFoxPhoto: FavouriteFoxPhoto)

}


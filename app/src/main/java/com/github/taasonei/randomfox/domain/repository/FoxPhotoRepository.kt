package com.github.taasonei.randomfox.domain.repository

import com.github.taasonei.randomfox.domain.model.FoxPhoto
import kotlinx.coroutines.flow.Flow

interface FoxPhotoRepository {

    suspend fun getLastFoxPhoto(): FoxPhoto?

    suspend fun getRandomFoxPhoto(): FoxPhoto

    suspend fun addToFavourites(foxPhoto: FoxPhoto): Long

    suspend fun deleteFromFavourites(foxPhoto: FoxPhoto)

    suspend fun getFavouriteFoxPhotoId(rowId: Long)

    fun getFavourites(): Flow<List<FoxPhoto>>

}

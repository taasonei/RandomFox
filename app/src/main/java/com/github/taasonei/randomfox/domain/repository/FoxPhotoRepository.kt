package com.github.taasonei.randomfox.domain.repository

import com.github.taasonei.randomfox.domain.model.FoxPhoto
import kotlinx.coroutines.flow.Flow

interface FoxPhotoRepository {

    suspend fun getLastFoxPhoto(): FoxPhoto?

    suspend fun getRandomFoxPhoto(): FoxPhoto

    suspend fun addToFavourites(foxPhoto: FoxPhoto)

    suspend fun deleteFromFavourites(foxPhoto: FoxPhoto)

    fun getFavourites(): Flow<List<FoxPhoto>>

}

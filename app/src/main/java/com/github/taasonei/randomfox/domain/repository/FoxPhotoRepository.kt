package com.github.taasonei.randomfox.domain.repository

import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import kotlinx.coroutines.flow.Flow

interface FoxPhotoRepository {

    suspend fun getLastFoxPhoto(): DomainFoxPhoto

    suspend fun setLastFoxPhoto(domainFoxPhoto: DomainFoxPhoto)

    suspend fun getRandomFoxPhoto(): DomainFoxPhoto

    suspend fun addToFavourites(domainFoxPhoto: DomainFoxPhoto): Long

    suspend fun deleteFromFavourites(domainFoxPhoto: DomainFoxPhoto)

    suspend fun getFavouriteFoxPhotoId(rowId: Long): Long

    fun getFavourites(): Flow<List<DomainFoxPhoto>>

}

package com.github.taasonei.randomfox.data.repository

import com.github.taasonei.randomfox.data.mapper.asFavouriteFoxPhoto
import com.github.taasonei.randomfox.data.source.local.FavouritesFoxPhotoDataSource
import com.github.taasonei.randomfox.data.source.local.LastFoxPhotoDataSource
import com.github.taasonei.randomfox.data.source.remote.NetworkFoxPhotoDataSource
import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository
import kotlinx.coroutines.flow.Flow

class FoxPhotoRepositoryImpl(
    private val favouritesFoxPhotoDataSource: FavouritesFoxPhotoDataSource,
    private val lastFoxPhotoDataSource: LastFoxPhotoDataSource,
    private val networkFoxPhotoDataSource: NetworkFoxPhotoDataSource
) : FoxPhotoRepository {

    override suspend fun getLastFoxPhoto(): DomainFoxPhoto? {
        return lastFoxPhotoDataSource.read()
    }

    override suspend fun setLastFoxPhoto(domainFoxPhoto: DomainFoxPhoto) {
        lastFoxPhotoDataSource.write(domainFoxPhoto)
    }

    override suspend fun getRandomFoxPhoto(): DomainFoxPhoto {
        return networkFoxPhotoDataSource.getRandomFoxPhoto()
    }

    override suspend fun addToFavourites(domainFoxPhoto: DomainFoxPhoto): Long {
        return favouritesFoxPhotoDataSource.add(domainFoxPhoto.asFavouriteFoxPhoto())
    }

    override suspend fun deleteFromFavourites(domainFoxPhoto: DomainFoxPhoto) {
        favouritesFoxPhotoDataSource.delete(domainFoxPhoto.asFavouriteFoxPhoto())
    }

    override suspend fun getFavouriteFoxPhotoId(rowId: Long): Long {
        return favouritesFoxPhotoDataSource.getId(rowId)
    }

    override fun getFavourites(): Flow<List<DomainFoxPhoto>> {
        return favouritesFoxPhotoDataSource.getListFoxPhoto()
    }

}

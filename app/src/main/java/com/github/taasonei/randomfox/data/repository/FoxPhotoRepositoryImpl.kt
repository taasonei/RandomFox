package com.github.taasonei.randomfox.data.repository

import com.github.taasonei.randomfox.data.mapper.asFavouriteFoxPhoto
import com.github.taasonei.randomfox.data.source.local.FavouritesFoxPhotoDataSource
import com.github.taasonei.randomfox.data.source.local.LastFoxPhotoDataSource
import com.github.taasonei.randomfox.data.source.remote.NetworkFoxPhotoDataSource
import com.github.taasonei.randomfox.domain.model.FoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository
import kotlinx.coroutines.flow.Flow

class FoxPhotoRepositoryImpl(
    private val favouritesFoxPhotoDataSource: FavouritesFoxPhotoDataSource,
    private val lastFoxPhotoDataSource: LastFoxPhotoDataSource,
    private val networkFoxPhotoDataSource: NetworkFoxPhotoDataSource
) : FoxPhotoRepository {

    override suspend fun getLastFoxPhoto(): FoxPhoto? {
        return lastFoxPhotoDataSource.read()
    }

    override suspend fun setLastFoxPhoto(foxPhoto: FoxPhoto) {
        lastFoxPhotoDataSource.write(foxPhoto)
    }

    override suspend fun getRandomFoxPhoto(): FoxPhoto {
        return networkFoxPhotoDataSource.getRandomFoxPhoto()
    }

    override suspend fun addToFavourites(foxPhoto: FoxPhoto): Long {
        return favouritesFoxPhotoDataSource.add(foxPhoto.asFavouriteFoxPhoto())
    }

    override suspend fun deleteFromFavourites(foxPhoto: FoxPhoto) {
        favouritesFoxPhotoDataSource.delete(foxPhoto.asFavouriteFoxPhoto())
    }

    override suspend fun getFavouriteFoxPhotoId(rowId: Long): Long {
        return favouritesFoxPhotoDataSource.getId(rowId)
    }

    override fun getFavourites(): Flow<List<FoxPhoto>> {
        return favouritesFoxPhotoDataSource.getListFoxPhoto()
    }

}

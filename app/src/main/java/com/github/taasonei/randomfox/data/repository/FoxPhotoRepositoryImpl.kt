package com.github.taasonei.randomfox.data.repository

import com.github.taasonei.randomfox.data.source.local.FavouritesFoxPhotoDataSource
import com.github.taasonei.randomfox.data.source.local.LastFoxPhotoDataSource
import com.github.taasonei.randomfox.data.source.model.local.FavouriteFoxPhoto
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
        TODO("Not yet implemented")
    }

    override suspend fun getRandomFoxPhoto(): FoxPhoto {
        return networkFoxPhotoDataSource.getRandomFoxPhoto()
    }

    override suspend fun addToFavourites(foxPhoto: FoxPhoto) {
        favouritesFoxPhotoDataSource.add(
            FavouriteFoxPhoto(
                id = foxPhoto.id,
                image = foxPhoto.image,
                link = foxPhoto.link
            )
        )
    }

    override suspend fun deleteFromFavourites(foxPhoto: FoxPhoto) {
        TODO("Not yet implemented")
    }

    override fun getFavourites(): Flow<List<FoxPhoto>> {
        TODO("Not yet implemented")
    }

}

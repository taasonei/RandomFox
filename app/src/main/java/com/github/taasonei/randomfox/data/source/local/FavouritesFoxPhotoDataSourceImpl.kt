package com.github.taasonei.randomfox.data.source.local

import com.github.taasonei.randomfox.data.mapper.asListFoxPhoto
import com.github.taasonei.randomfox.data.source.model.local.FavouriteFoxPhoto
import com.github.taasonei.randomfox.domain.model.FoxPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouritesFoxPhotoDataSourceImpl(
    private val database: FoxPhotoDatabase
) : FavouritesFoxPhotoDataSource {

    override fun getListFoxPhoto(): Flow<List<FoxPhoto>> {
        return database.favouritesFoxPhotoDao().getFavourites().map { list ->
            list.asListFoxPhoto()
        }
    }

    override suspend fun add(favouriteFoxPhoto: FavouriteFoxPhoto) {
        database.favouritesFoxPhotoDao().insert(favouriteFoxPhoto)
    }

    override suspend fun delete(favouriteFoxPhoto: FavouriteFoxPhoto) {
        database.favouritesFoxPhotoDao().delete(favouriteFoxPhoto)
    }

}

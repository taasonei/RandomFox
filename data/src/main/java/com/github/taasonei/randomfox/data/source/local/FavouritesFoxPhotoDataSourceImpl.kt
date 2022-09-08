package com.github.taasonei.randomfox.data.source.local

import com.github.taasonei.randomfox.data.mapper.asListFoxPhoto
import com.github.taasonei.randomfox.data.source.model.local.FavouriteFoxPhoto
import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouritesFoxPhotoDataSourceImpl(
    private val database: FoxPhotoDatabase
) : FavouritesFoxPhotoDataSource {

    override fun getListFoxPhoto(): Flow<List<DomainFoxPhoto>> {
        return database.favouritesFoxPhotoDao().getFavourites().map { list ->
            list.asListFoxPhoto()
        }
    }

    override suspend fun add(favouriteFoxPhoto: FavouriteFoxPhoto): Long {
        return database.favouritesFoxPhotoDao().insert(favouriteFoxPhoto = favouriteFoxPhoto)
    }

    override suspend fun delete(favouriteFoxPhoto: FavouriteFoxPhoto) {
        database.favouritesFoxPhotoDao().delete(favouriteFoxPhoto = favouriteFoxPhoto)
    }

    override suspend fun getId(rowId: Long): Long {
        return database.favouritesFoxPhotoDao().getPhotoIdByRowId(rowId = rowId)
    }

}

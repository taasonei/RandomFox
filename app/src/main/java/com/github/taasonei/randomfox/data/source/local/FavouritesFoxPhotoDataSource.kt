package com.github.taasonei.randomfox.data.source.local

import com.github.taasonei.randomfox.data.source.model.local.FavouriteFoxPhoto
import com.github.taasonei.randomfox.domain.model.FoxPhoto
import kotlinx.coroutines.flow.Flow
import java.sql.RowId

interface FavouritesFoxPhotoDataSource {

    fun getListFoxPhoto(): Flow<List<FoxPhoto>>

    suspend fun add(favouriteFoxPhoto: FavouriteFoxPhoto): Long

    suspend fun delete(favouriteFoxPhoto: FavouriteFoxPhoto)

    suspend fun getId(rowId: Long): Long

}

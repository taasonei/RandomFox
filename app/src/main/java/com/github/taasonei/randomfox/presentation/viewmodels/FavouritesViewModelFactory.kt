package com.github.taasonei.randomfox.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.taasonei.randomfox.data.repository.FoxPhotoRepositoryImpl
import com.github.taasonei.randomfox.data.source.local.FavouritesFoxPhotoDataSource
import com.github.taasonei.randomfox.data.source.local.FavouritesFoxPhotoDataSourceImpl
import com.github.taasonei.randomfox.data.source.local.FoxPhotoDatabase
import com.github.taasonei.randomfox.data.source.local.LastFoxPhotoDataSource
import com.github.taasonei.randomfox.data.source.remote.FoxPhotoApi
import com.github.taasonei.randomfox.data.source.remote.NetworkFoxPhotoDataSource
import com.github.taasonei.randomfox.data.source.remote.NetworkFoxPhotoDataSourceImpl
import com.github.taasonei.randomfox.domain.usecase.AddFoxPhotoToFavouritesUseCase
import com.github.taasonei.randomfox.domain.usecase.DeleteFoxPhotoFromFavouritesUseCase
import com.github.taasonei.randomfox.domain.usecase.GetFavouritesFoxPhotoUseCase

class FavouritesViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val foxPhotoDatabase = FoxPhotoDatabase.getDatabase(context = context)

    private val favouritesFoxPhotoDataSource: FavouritesFoxPhotoDataSource =
        FavouritesFoxPhotoDataSourceImpl(database = foxPhotoDatabase)

    private val networkFoxPhotoDataSource: NetworkFoxPhotoDataSource =
        NetworkFoxPhotoDataSourceImpl(foxPhotoApi = FoxPhotoApi)

    private val lastFoxPhotoDataSource: LastFoxPhotoDataSource = TODO()

    private val foxPhotoRepository = FoxPhotoRepositoryImpl(
        favouritesFoxPhotoDataSource = favouritesFoxPhotoDataSource,
        lastFoxPhotoDataSource = lastFoxPhotoDataSource,
        networkFoxPhotoDataSource = networkFoxPhotoDataSource
    )

    private val getFavouritesFoxPhotoUseCase =
        GetFavouritesFoxPhotoUseCase(foxPhotoRepository = foxPhotoRepository)

    private val addFoxPhotoToFavouritesUseCase =
        AddFoxPhotoToFavouritesUseCase(foxPhotoRepository = foxPhotoRepository)

    private val deleteFoxPhotoFromFavouritesUseCase =
        DeleteFoxPhotoFromFavouritesUseCase(foxPhotoRepository = foxPhotoRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouritesViewModel(
            getFavouritesFoxPhotoUseCase = getFavouritesFoxPhotoUseCase,
            addFoxPhotoToFavouritesUseCase = addFoxPhotoToFavouritesUseCase,
            deleteFoxPhotoFromFavouritesUseCase = deleteFoxPhotoFromFavouritesUseCase
        ) as T
    }

}
package com.github.taasonei.randomfox.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.taasonei.randomfox.data.repository.FoxPhotoRepositoryImpl
import com.github.taasonei.randomfox.data.source.local.*
import com.github.taasonei.randomfox.data.source.remote.FoxPhotoApi
import com.github.taasonei.randomfox.data.source.remote.NetworkFoxPhotoDataSource
import com.github.taasonei.randomfox.data.source.remote.NetworkFoxPhotoDataSourceImpl
import com.github.taasonei.randomfox.domain.usecase.*

class RecentViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val foxPhotoDatabase = FoxPhotoDatabase.getDatabase(context = context)

    private val favouritesFoxPhotoDataSource: FavouritesFoxPhotoDataSource =
        FavouritesFoxPhotoDataSourceImpl(database = foxPhotoDatabase)

    private val networkFoxPhotoDataSource: NetworkFoxPhotoDataSource =
        NetworkFoxPhotoDataSourceImpl(foxPhotoApi = FoxPhotoApi)

    private val lastFoxPhotoDataSource: LastFoxPhotoDataSource =
        LastFoxPhotoDataSourceImpl(context = context)

    private val foxPhotoRepository = FoxPhotoRepositoryImpl(
        favouritesFoxPhotoDataSource = favouritesFoxPhotoDataSource,
        lastFoxPhotoDataSource = lastFoxPhotoDataSource,
        networkFoxPhotoDataSource = networkFoxPhotoDataSource
    )

    private val getLastFoxPhotoUseCase =
        GetLastFoxPhotoUseCase(foxPhotoRepository = foxPhotoRepository)

    private val setLastFoxPhotoUseCase =
        SetLastFoxPhotoUseCase(foxPhotoRepository = foxPhotoRepository)

    private val getRandomFoxPhotoUseCase =
        GetRandomFoxPhotoUseCase(foxPhotoRepository = foxPhotoRepository)

    private val addFoxPhotoToFavouritesUseCase =
        AddFoxPhotoToFavouritesUseCase(foxPhotoRepository = foxPhotoRepository)

    private val deleteFoxPhotoFromFavouritesUseCase =
        DeleteFoxPhotoFromFavouritesUseCase(foxPhotoRepository = foxPhotoRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecentViewModel(
            getLastFoxPhotoUseCase = getLastFoxPhotoUseCase,
            setLastFoxPhotoUseCase = setLastFoxPhotoUseCase,
            getRandomFoxPhotoUseCase = getRandomFoxPhotoUseCase,
            addFoxPhotoToFavouritesUseCase = addFoxPhotoToFavouritesUseCase,
            deleteFoxPhotoFromFavouritesUseCase = deleteFoxPhotoFromFavouritesUseCase
        ) as T
    }
}

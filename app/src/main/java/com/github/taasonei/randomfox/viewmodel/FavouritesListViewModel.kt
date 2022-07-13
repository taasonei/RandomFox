package com.github.taasonei.randomfox.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.github.taasonei.randomfox.model.FoxPhoto
import com.github.taasonei.randomfox.repository.FoxRepository
import kotlinx.coroutines.flow.Flow

class FavouritesListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FoxRepository(application.applicationContext)

    val listFoxPhoto: Flow<List<FoxPhoto>> = repository.getFoxPhotosFromDatabase()

}

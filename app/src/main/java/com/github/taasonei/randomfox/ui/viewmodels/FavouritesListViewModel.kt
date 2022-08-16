package com.github.taasonei.randomfox.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.taasonei.randomfox.data.database.DatabaseFox
import com.github.taasonei.randomfox.ui.model.FoxPhoto
import com.github.taasonei.randomfox.data.repository.FoxRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavouritesListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FoxRepository(application.applicationContext)

    private val _foxPhoto = MutableLiveData<FoxPhoto>()
    val foxPhoto: LiveData<FoxPhoto>
        get() = _foxPhoto

    val listFoxPhoto: Flow<List<FoxPhoto>> = repository.getFoxPhotosFromDatabase()

    fun deleteFromFavourites(foxPhoto: FoxPhoto) {
        if (foxPhoto.link.isNotBlank() && foxPhoto.image.isNotBlank()) {
            _foxPhoto.value = foxPhoto
            viewModelScope.launch {
                val id = foxPhoto.id
                if (id != null) {
                    repository.delete(
                        DatabaseFox(
                            id = id,
                            link = foxPhoto.link,
                            image = foxPhoto.image
                        )
                    )
                }
            }
        }
    }

    fun insertToFavourites(foxPhoto: FoxPhoto) {
        val id = foxPhoto.id
        if (id != null && foxPhoto.link.isNotBlank() && foxPhoto.image.isNotBlank()) {
            viewModelScope.launch {
                repository.insert(
                    DatabaseFox(
                        id = id,
                        link = foxPhoto.link,
                        image = foxPhoto.image
                    )
                )
            }
        }
    }

}

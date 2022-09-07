package com.github.taasonei.randomfox.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.taasonei.randomfox.domain.model.FoxPhoto
import com.github.taasonei.randomfox.domain.usecase.AddFoxPhotoToFavouritesUseCase
import com.github.taasonei.randomfox.domain.usecase.DeleteFoxPhotoFromFavouritesUseCase
import com.github.taasonei.randomfox.domain.usecase.GetFavouritesFoxPhotoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavouritesViewModel(
    getFavouritesFoxPhotoUseCase: GetFavouritesFoxPhotoUseCase,
    private val addFoxPhotoToFavouritesUseCase: AddFoxPhotoToFavouritesUseCase,
    private val deleteFoxPhotoFromFavouritesUseCase: DeleteFoxPhotoFromFavouritesUseCase
) : ViewModel() {

    private val _foxPhoto = MutableLiveData<FoxPhoto>()
    val foxPhoto: LiveData<FoxPhoto>
        get() = _foxPhoto

    val listFoxPhoto: Flow<List<FoxPhoto>> = getFavouritesFoxPhotoUseCase.execute()

    fun deleteFromFavourites(foxPhoto: FoxPhoto) {
        if (foxPhoto.id != null) {
            _foxPhoto.value = foxPhoto

            viewModelScope.launch {
                deleteFoxPhotoFromFavouritesUseCase.execute(foxPhoto)
            }
        }
    }

    fun insertToFavourites(foxPhoto: FoxPhoto) {
        if (foxPhoto.id != null && foxPhoto.link.isNotBlank() && foxPhoto.image.isNotBlank()) {
            viewModelScope.launch {
                addFoxPhotoToFavouritesUseCase.execute(foxPhoto)
            }
        }
    }

}

package com.github.taasonei.randomfox.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.taasonei.randomfox.domain.model.FoxPhoto
import com.github.taasonei.randomfox.domain.usecase.AddFoxPhotoToFavouritesUseCase
import com.github.taasonei.randomfox.domain.usecase.DeleteFoxPhotoFromFavouritesUseCase
import com.github.taasonei.randomfox.domain.usecase.GetLastFoxPhotoUseCase
import com.github.taasonei.randomfox.domain.usecase.GetRandomFoxPhotoUseCase
import com.github.taasonei.randomfox.presentation.model.Status
import kotlinx.coroutines.launch
import java.io.IOException

class RecentViewModel(
    private val getLastFoxPhotoUseCase: GetLastFoxPhotoUseCase,
    private val getRandomFoxPhotoUseCase: GetRandomFoxPhotoUseCase,
    private val addFoxPhotoToFavouritesUseCase: AddFoxPhotoToFavouritesUseCase,
    private val deleteFoxPhotoFromFavouritesUseCase: DeleteFoxPhotoFromFavouritesUseCase
) : ViewModel() {

    private var _foxPhoto = MutableLiveData<FoxPhoto>()
    val foxPhoto: LiveData<FoxPhoto>
        get() = _foxPhoto

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    init {
        viewModelScope.launch {
            try {
                _foxPhoto.value = getLastFoxPhotoUseCase.execute()
                _status.value = Status.Success
            } catch (e: IllegalStateException) {
                _status.value = Status.Error(e.message ?: "Something went wrong")
                Log.d("tag", e.stackTraceToString())
            } catch (e: IOException) {
                _status.value = Status.Error(e.message ?: "Can't read datastore")
                Log.d("tag", e.stackTraceToString())
            } catch (e: NoSuchElementException) {
                _status.value = Status.Error(e.message ?: "No such value in datastore")
                Log.d("tag", e.stackTraceToString())
            } catch (e: Exception) {
                _status.value = Status.Error(e.message ?: "Something went wrong")
                Log.d("tag", e.stackTraceToString())
            }
        }
    }

    fun getFoxPhoto() {
        viewModelScope.launch {
            try {
                _foxPhoto.value = getRandomFoxPhotoUseCase.execute()
                _status.value = Status.Success
            } catch (e: IllegalStateException) {
                _status.value = Status.Error(e.message ?: "Something went wrong")
                Log.d("tag", e.stackTraceToString())
            } catch (e: Exception) {
                _status.value = Status.Error(e.message ?: "Something went wrong")
                Log.d("tag", e.stackTraceToString())
            }
        }
    }

    fun insertToFavourites() {
        val link = foxPhoto.value?.link
        val image = foxPhoto.value?.image

        if (!link.isNullOrBlank() && !image.isNullOrBlank()) {
            viewModelScope.launch {
                foxPhoto.value?.let { foxPhoto ->
                    foxPhoto.id = addFoxPhotoToFavouritesUseCase.execute(foxPhoto)
                    foxPhoto.isFavourite = true
                }
            }
        }
    }

    fun deleteFromFavourites() {
        val id = foxPhoto.value?.id
        val link = foxPhoto.value?.link
        val image = foxPhoto.value?.image

        if (id != null && !link.isNullOrBlank() && !image.isNullOrBlank()) {
            viewModelScope.launch {
                foxPhoto.value?.let { foxPhoto ->
                    deleteFoxPhotoFromFavouritesUseCase.execute(foxPhoto)
                    foxPhoto.isFavourite = false
                    foxPhoto.id = null
                }
            }
        }
    }

}

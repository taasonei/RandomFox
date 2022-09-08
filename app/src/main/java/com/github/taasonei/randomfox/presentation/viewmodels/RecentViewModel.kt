package com.github.taasonei.randomfox.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.taasonei.randomfox.domain.usecase.*
import com.github.taasonei.randomfox.presentation.mapper.asDomainFoxPhoto
import com.github.taasonei.randomfox.presentation.mapper.asFoxPhoto
import com.github.taasonei.randomfox.presentation.model.FoxPhoto
import com.github.taasonei.randomfox.presentation.model.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecentViewModel(
    private val getLastFoxPhotoUseCase: GetLastFoxPhotoUseCase,
    private val setLastFoxPhotoUseCase: SetLastFoxPhotoUseCase,
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
                withContext(Dispatchers.IO) {
                    _foxPhoto.postValue(getLastFoxPhotoUseCase.execute().asFoxPhoto())
                }
                _status.value = Status.Success
            } catch (e: Exception) {
                _status.value = Status.Error(e.message ?: "Something went wrong")
                Log.d("tag", e.stackTraceToString())
            }
        }
    }

    fun getFoxPhoto() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    _foxPhoto.postValue(getRandomFoxPhotoUseCase.execute().asFoxPhoto())
                }
                foxPhoto.value?.let { foxPhoto ->
                    setLastFoxPhotoUseCase.execute(foxPhoto.asDomainFoxPhoto())
                }
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
            viewModelScope.launch(Dispatchers.IO) {
                foxPhoto.value?.let { foxPhoto ->
                    foxPhoto.id =
                        addFoxPhotoToFavouritesUseCase.execute(foxPhoto.asDomainFoxPhoto())
                    foxPhoto.isFavourite = true
                    setLastFoxPhotoUseCase.execute(foxPhoto.asDomainFoxPhoto())
                }
            }
        }
    }

    fun deleteFromFavourites() {
        val id = foxPhoto.value?.id
        val link = foxPhoto.value?.link
        val image = foxPhoto.value?.image

        if (id != null && !link.isNullOrBlank() && !image.isNullOrBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                foxPhoto.value?.let { foxPhoto ->
                    deleteFoxPhotoFromFavouritesUseCase.execute(foxPhoto.asDomainFoxPhoto())
                    foxPhoto.isFavourite = false
                    foxPhoto.id = null
                    setLastFoxPhotoUseCase.execute(foxPhoto.asDomainFoxPhoto())
                }
            }
        }
    }

}

package com.github.taasonei.randomfox.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.taasonei.randomfox.database.DatabaseFox
import com.github.taasonei.randomfox.model.FoxPhoto
import com.github.taasonei.randomfox.model.Status
import com.github.taasonei.randomfox.repository.FoxRepository
import kotlinx.coroutines.launch
import java.io.IOException

class RecentImageViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FoxRepository(application.applicationContext)

    private var _foxPhoto = MutableLiveData<FoxPhoto>()
    val foxPhoto: LiveData<FoxPhoto>
        get() = _foxPhoto

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    init {
        viewModelScope.launch {
            try {
                _foxPhoto.value = repository.firstLoadFoxPhoto()
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
                _foxPhoto.value = repository.loadFoxPhoto()
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
        val id = foxPhoto.value?.link
        val imageLink = foxPhoto.value?.image
        if (!id.isNullOrBlank() && !imageLink.isNullOrBlank()) {
            viewModelScope.launch {
                repository.insert(
                    DatabaseFox(
                        id = id,
                        imageLink = imageLink
                    )
                )
                repository.writeData(
                    FoxPhoto(
                        link = id,
                        image = imageLink,
                        isFavourite = true
                    )
                )
            }
        }
    }

    fun deleteFromFavourites() {
        val id = foxPhoto.value?.link
        val imageLink = foxPhoto.value?.image

        if (!id.isNullOrBlank() && !imageLink.isNullOrBlank()) {
            viewModelScope.launch {
                repository.delete(
                    DatabaseFox(
                        id = id,
                        imageLink = imageLink
                    )
                )
                repository.writeData(
                    FoxPhoto(
                        link = id,
                        image = imageLink,
                        isFavourite = false
                    )
                )
            }
        }
    }

}

package com.github.taasonei.randomfox.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.taasonei.randomfox.data.database.DatabaseFox
import com.github.taasonei.randomfox.presentation.model.FoxPhoto
import com.github.taasonei.randomfox.presentation.model.Status
import com.github.taasonei.randomfox.data.repository.FoxRepository
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
        val id = foxPhoto.value?.id
        val link = foxPhoto.value?.link
        val image = foxPhoto.value?.image
        Log.d("tag", "insert $id $link $image")

        if (!link.isNullOrBlank() && !image.isNullOrBlank()) {
            viewModelScope.launch {
                Log.d("tag", "currentId $id")

                val rowId = if (id != null) {
                    repository.insert(DatabaseFox(id = id, link = link, image = image))
                } else {
                    repository.insert(DatabaseFox(link = link, image = image))
                }

                val dbId = repository.getFoxPhotoId(rowId)
                _foxPhoto.value?.isFavourite = true
                _foxPhoto.value?.id = dbId

                repository.writeData(
                    FoxPhoto(
                        id = dbId,
                        link = link,
                        image = image,
                        isFavourite = true
                    )
                )
            }

            Log.d("tag", "foxPhoto after inserting ${foxPhoto.value}")
        }
    }

    fun deleteFromFavourites() {
        val id = foxPhoto.value?.id
        val link = foxPhoto.value?.link
        val image = foxPhoto.value?.image

        Log.d("tag", "$id $link $image")

        if (id != null && !link.isNullOrBlank() && !image.isNullOrBlank()) {
            viewModelScope.launch {
                repository.delete(
                    DatabaseFox(
                        id = id,
                        link = link,
                        image = image
                    )
                )
                repository.writeData(
                    FoxPhoto(
                        id = null,
                        link = link,
                        image = image,
                        isFavourite = false
                    )
                )

                _foxPhoto.value?.isFavourite = false
                _foxPhoto.value?.id = null
            }
        }
    }

}

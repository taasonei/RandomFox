package com.github.taasonei.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.taasonei.database.FavouriteFoxPhoto
import com.github.taasonei.database.FavouritesRoomDatabase
import com.github.taasonei.model.FoxPhoto
import com.github.taasonei.model.Status
import com.github.taasonei.network.FoxApi
import kotlinx.coroutines.launch

class RecentImageViewModel(application: Application) : AndroidViewModel(application) {

    private val favouritesDao = FavouritesRoomDatabase
        .getDatabase(application.applicationContext)
        .favouritesDao()

    private var _foxPhoto = MutableLiveData<FoxPhoto>()
    val foxPhoto: LiveData<FoxPhoto>
        get() = _foxPhoto

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    init {
        getFoxPhoto()
    }

    fun getFoxPhoto() {
        viewModelScope.launch {
            try {
                _foxPhoto.value = FoxApi.retrofitService.getPhoto()
                val favouriteFoxPhoto = createFavouriteFoxPhoto()

                if (favouriteFoxPhoto != null) {
                    val dbResult = getFavouriteFoxPhoto(favouriteFoxPhoto)

                    if (dbResult != null) {
                        foxPhoto.value?.isFavourite = true
                    }
                }

                _status.value = Status.Success
                Log.d("tag", "${foxPhoto.value?.link}")
            } catch (e: Exception) {
                _status.value = Status.Error(e.toString())
                Log.d("tag", e.toString())
            }
        }
    }

    private fun createFavouriteFoxPhoto(): FavouriteFoxPhoto? {
        val photo = foxPhoto.value
        return if (photo != null && photo.link.isNotBlank() && photo.image.isNotBlank())
            FavouriteFoxPhoto(
                id = photo.link,
                imageLink = photo.image
            ) else null
    }

    fun insertToFavourites() {
        val favouriteFox = createFavouriteFoxPhoto()

        if (favouriteFox != null) {
            viewModelScope.launch {
                favouritesDao.insertPhoto(favouriteFox)
            }
        }
    }

    fun deleteFromFavourites() {
        val favouriteFox = createFavouriteFoxPhoto()

        if (favouriteFox != null) {
            viewModelScope.launch {
                favouritesDao.deletePhoto(favouriteFox)
            }
        }
    }

    private suspend fun getFavouriteFoxPhoto(favouriteFoxPhoto: FavouriteFoxPhoto): FavouriteFoxPhoto? {
        return favouritesDao.getPhoto(favouriteFoxPhoto.id)
    }

}
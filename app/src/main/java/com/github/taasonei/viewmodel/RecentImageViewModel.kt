package com.github.taasonei.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.taasonei.model.FoxPhoto
import com.github.taasonei.model.Status
import com.github.taasonei.network.FoxApi
import kotlinx.coroutines.launch
import java.lang.Exception

class RecentImageViewModel : ViewModel() {
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
                _status.value = Status.Success
                _foxPhoto.value = FoxApi.retrofitService.getPhoto()
                Log.d("tag", _foxPhoto.value.toString())
            } catch (e: Exception) {
                _status.value = Status.Error(e.toString())
                Log.d("tag", e.toString())
            }
        }
    }
}
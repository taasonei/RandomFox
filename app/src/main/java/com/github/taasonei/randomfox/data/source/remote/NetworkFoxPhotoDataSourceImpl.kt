package com.github.taasonei.randomfox.data.source.remote

import com.github.taasonei.randomfox.data.source.model.remote.asFoxPhoto
import com.github.taasonei.randomfox.domain.model.FoxPhoto

class NetworkFoxPhotoDataSourceImpl(
    private val foxPhotoApiService: FoxPhotoApiService
) : NetworkFoxPhotoDataSource {

    override suspend fun getRandomFoxPhoto(): FoxPhoto {
        val networkFoxPhoto = foxPhotoApiService.getFoxPhoto()

        if (networkFoxPhoto.image != null && networkFoxPhoto.link != null) {
            return networkFoxPhoto.asFoxPhoto()
        } else {
            throw IllegalArgumentException("Get null property when waiting for string")
        }
    }

}

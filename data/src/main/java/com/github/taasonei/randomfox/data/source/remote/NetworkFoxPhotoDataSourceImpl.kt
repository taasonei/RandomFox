package com.github.taasonei.randomfox.data.source.remote

import com.github.taasonei.randomfox.data.mapper.asFoxPhoto
import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto

class NetworkFoxPhotoDataSourceImpl(
    private val foxPhotoApi: FoxPhotoApi
) : NetworkFoxPhotoDataSource {

    override suspend fun getRandomFoxPhoto(): DomainFoxPhoto {
        val networkFoxPhoto = foxPhotoApi.retrofitService.getFoxPhoto()

        if (networkFoxPhoto.image != null && networkFoxPhoto.link != null) {
            return networkFoxPhoto.asFoxPhoto()
        } else {
            throw IllegalArgumentException("Get null property when waiting for string")
        }
    }

}

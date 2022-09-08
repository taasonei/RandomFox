package com.github.taasonei.randomfox.domain.usecase

import android.util.Log
import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository

class GetLastFoxPhotoUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    suspend fun execute(): DomainFoxPhoto {
        return try {
            foxPhotoRepository.getLastFoxPhoto() ?: foxPhotoRepository.getRandomFoxPhoto()
        } catch (e: Exception) {
            val domainFoxPhoto = foxPhotoRepository.getRandomFoxPhoto()
            foxPhotoRepository.setLastFoxPhoto(domainFoxPhoto)
            domainFoxPhoto
        }
    }

}

package com.github.taasonei.randomfox.domain.usecase

import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository

class GetLastFoxPhotoUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    suspend fun execute(): DomainFoxPhoto {
        return try {
            val lastFox = foxPhotoRepository.getLastFoxPhoto()
            return if (lastFox.image.isNotBlank() || lastFox.link.isNotBlank()) {
                lastFox
            } else {
                getRandomFoxPhotoAndSaveToLast()
            }
        } catch (e: Exception) {
            getRandomFoxPhotoAndSaveToLast()
        }
    }

    private suspend fun getRandomFoxPhotoAndSaveToLast(): DomainFoxPhoto {
        val domainFoxPhoto = foxPhotoRepository.getRandomFoxPhoto()
        foxPhotoRepository.setLastFoxPhoto(domainFoxPhoto)
        return domainFoxPhoto
    }

}

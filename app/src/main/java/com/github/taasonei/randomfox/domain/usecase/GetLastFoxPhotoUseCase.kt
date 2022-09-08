package com.github.taasonei.randomfox.domain.usecase

import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository

class GetLastFoxPhotoUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    suspend fun execute(): DomainFoxPhoto {
        return foxPhotoRepository.getLastFoxPhoto() ?: foxPhotoRepository.getRandomFoxPhoto()
    }

}

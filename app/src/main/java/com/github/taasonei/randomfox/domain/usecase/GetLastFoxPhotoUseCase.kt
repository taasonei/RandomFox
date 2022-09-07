package com.github.taasonei.randomfox.domain.usecase

import com.github.taasonei.randomfox.domain.model.FoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository

class GetLastFoxPhotoUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    suspend fun execute(): FoxPhoto {
        return foxPhotoRepository.getLastFoxPhoto() ?: foxPhotoRepository.getRandomFoxPhoto()
    }

}

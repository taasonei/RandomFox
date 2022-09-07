package com.github.taasonei.randomfox.domain.usecase

import com.github.taasonei.randomfox.domain.model.FoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository

class GetRandomFoxPhotoUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    suspend fun execute(): FoxPhoto {
        return foxPhotoRepository.getRandomFoxPhoto()
    }

}

package com.github.taasonei.randomfox.domain.usecase

import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository

class SetLastFoxPhotoUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    suspend fun execute(domainFoxPhoto: DomainFoxPhoto) {
        foxPhotoRepository.setLastFoxPhoto(domainFoxPhoto)
    }

}

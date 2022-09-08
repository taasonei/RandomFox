package com.github.taasonei.randomfox.domain.usecase

import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository

class DeleteFoxPhotoFromFavouritesUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    suspend fun execute(domainFoxPhoto: DomainFoxPhoto) {
        foxPhotoRepository.deleteFromFavourites(domainFoxPhoto = domainFoxPhoto)
    }

}

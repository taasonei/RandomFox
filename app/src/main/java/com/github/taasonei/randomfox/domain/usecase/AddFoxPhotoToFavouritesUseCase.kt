package com.github.taasonei.randomfox.domain.usecase

import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository

class AddFoxPhotoToFavouritesUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    suspend fun execute(domainFoxPhoto: DomainFoxPhoto): Long {
        val rowId = if (domainFoxPhoto.id != null) {
            foxPhotoRepository.addToFavourites(domainFoxPhoto)
        } else {
            foxPhotoRepository.addToFavourites(DomainFoxPhoto(image = domainFoxPhoto.image, link = domainFoxPhoto.link))
        }

        return foxPhotoRepository.getFavouriteFoxPhotoId(rowId)
    }

}
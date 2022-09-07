package com.github.taasonei.randomfox.domain.usecase

import com.github.taasonei.randomfox.domain.model.FoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository

class AddFoxPhotoToFavouritesUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    suspend fun execute(foxPhoto: FoxPhoto): Long {
        val rowId = if (foxPhoto.id != null) {
            foxPhotoRepository.addToFavourites(foxPhoto)
        } else {
            foxPhotoRepository.addToFavourites(FoxPhoto(image = foxPhoto.image, link = foxPhoto.link))
        }

        return foxPhotoRepository.getFavouriteFoxPhotoId(rowId)
    }

}
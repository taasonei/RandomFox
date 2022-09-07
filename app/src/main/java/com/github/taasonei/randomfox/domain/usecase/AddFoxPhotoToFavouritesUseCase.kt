package com.github.taasonei.randomfox.domain.usecase

import com.github.taasonei.randomfox.domain.model.FoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository

class AddFoxPhotoToFavouritesUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    suspend fun execute(foxPhoto: FoxPhoto) {
        foxPhotoRepository.addToFavourites(foxPhoto = foxPhoto)
    }

}
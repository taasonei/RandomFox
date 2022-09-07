package com.github.taasonei.randomfox.domain.usecase

import com.github.taasonei.randomfox.domain.model.FoxPhoto
import com.github.taasonei.randomfox.domain.repository.FoxPhotoRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesFoxPhotoUseCase(private val foxPhotoRepository: FoxPhotoRepository) {

    fun execute(): Flow<List<FoxPhoto>> {
        return foxPhotoRepository.getFavourites()
    }

}

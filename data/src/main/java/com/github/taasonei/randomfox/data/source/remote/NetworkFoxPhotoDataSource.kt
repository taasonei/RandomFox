package com.github.taasonei.randomfox.data.source.remote

import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto

interface NetworkFoxPhotoDataSource {

    suspend fun getRandomFoxPhoto(): DomainFoxPhoto

}

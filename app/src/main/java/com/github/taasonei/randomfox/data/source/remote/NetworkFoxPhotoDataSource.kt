package com.github.taasonei.randomfox.data.source.remote

import com.github.taasonei.randomfox.domain.model.FoxPhoto

interface NetworkFoxPhotoDataSource {

    suspend fun getRandomFoxPhoto(): FoxPhoto

}

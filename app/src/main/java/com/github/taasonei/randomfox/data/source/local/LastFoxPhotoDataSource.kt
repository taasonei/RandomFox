package com.github.taasonei.randomfox.data.source.local

import com.github.taasonei.randomfox.domain.model.FoxPhoto

interface LastFoxPhotoDataSource {

    suspend fun read(): FoxPhoto?

    suspend fun write(foxPhoto: FoxPhoto)

}

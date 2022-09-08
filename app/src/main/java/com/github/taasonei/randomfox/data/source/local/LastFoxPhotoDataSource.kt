package com.github.taasonei.randomfox.data.source.local

import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto

interface LastFoxPhotoDataSource {

    suspend fun read(): DomainFoxPhoto?

    suspend fun write(domainFoxPhoto: DomainFoxPhoto)

}

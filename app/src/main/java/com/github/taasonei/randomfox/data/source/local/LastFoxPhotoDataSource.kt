package com.github.taasonei.randomfox.data.source.local

import com.github.taasonei.randomfox.data.LastFox
import com.github.taasonei.randomfox.domain.model.FoxPhoto

interface LastFoxPhotoDataSource {

    suspend fun getLastFox(): FoxPhoto?

    suspend fun upsert(lastFox: LastFox)

}

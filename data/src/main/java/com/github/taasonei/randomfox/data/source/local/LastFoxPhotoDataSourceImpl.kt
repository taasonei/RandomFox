package com.github.taasonei.randomfox.data.source.local

import android.content.Context
import com.github.taasonei.randomfox.data.source.MoshiService
import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import java.io.File

private const val FILENAME = "lastfox"

class LastFoxPhotoDataSourceImpl(context: Context) : LastFoxPhotoDataSource {

    private val file = File(context.filesDir, FILENAME)

    override suspend fun read(): DomainFoxPhoto {
        val json = file.readText()
        return MoshiService.foxPhotoFromJson(json = json)
    }

    override suspend fun write(domainFoxPhoto: DomainFoxPhoto) {
        val json = MoshiService.foxPhotoToJson(domainFoxPhoto = domainFoxPhoto)
        file.writeText(json)
    }

}

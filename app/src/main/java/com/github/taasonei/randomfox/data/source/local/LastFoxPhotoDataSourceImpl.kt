package com.github.taasonei.randomfox.data.source.local

import android.content.Context
import com.github.taasonei.randomfox.data.source.MoshiService
import com.github.taasonei.randomfox.domain.model.FoxPhoto
import java.io.File

private const val FILENAME = "lastfox"

class LastFoxPhotoDataSourceImpl(context: Context) : LastFoxPhotoDataSource {

    private val file = File(context.filesDir, FILENAME)

    override suspend fun read(): FoxPhoto {
        val json = file.readText()
        return MoshiService.foxPhotoFromJson(json)
    }

    override suspend fun write(foxPhoto: FoxPhoto) {
        MoshiService.foxPhotoToJson(foxPhoto)
    }

}

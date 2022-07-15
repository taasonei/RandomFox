package com.github.taasonei.randomfox.data

import android.content.Context
import android.util.Log
import com.github.taasonei.randomfox.model.FoxPhoto
import com.github.taasonei.randomfox.network.MoshiService
import java.io.File

private const val FILENAME = "lastfox"

class LastFox(context: Context) {

    private val file = File(context.filesDir, FILENAME)

    fun writeFile(foxPhoto: FoxPhoto) {
        val json = MoshiService.foxPhotoToJson(foxPhoto)
        file.writeText(json)
    }

    fun readFile(): FoxPhoto {
        Log.d("lastfox", "")
        val json = file.readText()
        return MoshiService.foxPhotoFromJson(json)
    }

}
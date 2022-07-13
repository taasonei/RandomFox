package com.github.taasonei.randomfox.data

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.github.taasonei.randomfox.LastFox
import java.io.InputStream
import java.io.OutputStream

object LastFoxSerializer : Serializer<LastFox> {
    override val defaultValue: LastFox =
        LastFox.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): LastFox {
        try {
            return LastFox.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: LastFox, output: OutputStream) = t.writeTo(output)
}

private const val LAST_FOX_DATASTORE = "last_fox_datastore"

val Context.dataStore: DataStore<LastFox> by dataStore(
    fileName = LAST_FOX_DATASTORE,
    serializer = LastFoxSerializer
)
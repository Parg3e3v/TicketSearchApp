package com.parg3v.domain.serializer

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object FieldFromSerializer: Serializer<String> {
    override val defaultValue: String
        get() = "Минск"

    override suspend fun readFrom(input: InputStream): String {
        return try {
            Json.decodeFromString(
                deserializer = String.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: String, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = String.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}
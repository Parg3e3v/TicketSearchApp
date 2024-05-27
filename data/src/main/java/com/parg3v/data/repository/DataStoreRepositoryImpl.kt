package com.parg3v.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.parg3v.data.config.DataStoreConfig
import com.parg3v.domain.common.FieldToError
import com.parg3v.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStorePreferences: DataStore<Preferences>
) : DataStoreRepository {

    override suspend fun setField(
        name: String
    ): String {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[DataStoreConfig.KEY_NAME] = name
            }
        }

        return name

    }

    override suspend fun getField(): Result<String> {
        return Result.runCatching {
            val flow = dataStorePreferences.data
                .catch { exception ->
                    /*
                     * dataStore.data throws an IOException when an error
                     * is encountered when reading data
                     */
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    // Get our name value, defaulting to "" if not set
                    preferences[DataStoreConfig.KEY_NAME]
                }
            val value = flow.firstOrNull() ?: "" // we only care about the 1st value
            value
        }
    }

}
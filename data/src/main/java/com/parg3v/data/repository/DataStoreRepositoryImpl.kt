package com.parg3v.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.parg3v.data.config.DataStoreConfig
import com.parg3v.data.extensions.dataStore
import com.parg3v.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
) : DataStoreRepository {

    override suspend fun setField(value: String) {
        val preferencesKey = stringPreferencesKey(DataStoreConfig.KEY_NAME)
        context.dataStore.edit { preferences -> preferences[preferencesKey] = value }
        Log.d("DATASTORE", "setField: complete ($value)")
    }

    override suspend fun getField(): String? {
        val preferencesKey = stringPreferencesKey(DataStoreConfig.KEY_NAME)
        val preferences = context.dataStore.data.first()
        return if (!preferences.contains(preferencesKey)) {
            setField(DataStoreConfig.DEFAULT_VALUE)
            getField()
        } else {
            preferences[preferencesKey]
        }
    }
}
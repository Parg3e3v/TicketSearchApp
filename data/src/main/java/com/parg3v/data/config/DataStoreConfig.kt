package com.parg3v.data.config

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreConfig {
    const val FILE_NAME = "from-field-info.json"
    val KEY_NAME = stringPreferencesKey("from-field-info")
}
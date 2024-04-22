package com.parg3v.domain.extensions

import android.content.Context
import androidx.datastore.dataStore
import com.parg3v.domain.config.Config
import com.parg3v.domain.serializer.FieldFromSerializer

val Context.dataStore by dataStore(Config.FILE_NAME, FieldFromSerializer)

package com.parg3v.domain.use_cases

import android.content.Context
import com.parg3v.domain.extensions.dataStore
import kotlinx.coroutines.flow.first

class GetFromFieldFromDataStore {
    suspend operator fun invoke(context: Context): String {
        return context.dataStore.data.first()
    }
}
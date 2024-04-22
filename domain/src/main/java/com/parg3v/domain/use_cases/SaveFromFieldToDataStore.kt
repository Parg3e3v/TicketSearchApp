package com.parg3v.domain.use_cases

import android.content.Context
import com.parg3v.domain.common.FieldFromError
import com.parg3v.domain.common.Result
import com.parg3v.domain.common.RootError
import com.parg3v.domain.extensions.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class SaveFromFieldToDataStore {
    operator fun invoke(context: Context, value: String): Flow<Result<String, RootError>> = flow {
        try {
            emit(Result.Loading())
            val fieldValue = context.dataStore.updateData { value }
            emit(Result.Success(fieldValue))
        }catch (e: IOException){
            emit(Result.Error(FieldFromError.BASIC))
        }
    }
}
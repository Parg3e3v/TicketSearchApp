package com.parg3v.domain.use_cases

import com.parg3v.domain.common.FieldFromError
import com.parg3v.domain.common.Result
import com.parg3v.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SaveFromFieldToDataStoreUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke(value: String): Flow<Result<String?, FieldFromError>> = flow {
        try {
            emit(Result.Loading())
            dataStoreRepository.setField(value)
            val fieldValue = dataStoreRepository.getField()
            emit(Result.Success(fieldValue))
        } catch (e: IOException) {
            emit(Result.Error(FieldFromError.BASIC))
        }
    }
}
package com.parg3v.domain.use_cases

import com.parg3v.domain.common.FieldToError
import com.parg3v.domain.common.Result
import com.parg3v.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetFromFieldFromDataStoreUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke(): Flow<Result<String, FieldToError>> = flow {
        try {
            emit(Result.Loading())
            val toField = dataStoreRepository.getField().getOrDefault("opa")
            emit(Result.Success(toField))

        } catch (e: IOException) {
            emit(Result.Error(FieldToError.BASIC))
        }
    }
}
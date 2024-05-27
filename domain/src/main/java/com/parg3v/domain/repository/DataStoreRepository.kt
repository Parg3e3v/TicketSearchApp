package com.parg3v.domain.repository

interface DataStoreRepository {

    suspend fun setField(
        name: String
    ): String

    suspend fun getField(): Result<String>
}

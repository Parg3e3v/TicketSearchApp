package com.parg3v.domain.repository

interface DataStoreRepository {

    suspend fun setField(value: String)

    suspend fun getField(): String?
}

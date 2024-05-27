package com.parg3v.domain.module

import com.parg3v.domain.repository.DataStoreRepository
import com.parg3v.domain.use_cases.GetFromFieldFromDataStoreUseCase
import com.parg3v.domain.use_cases.SaveFromFieldToDataStoreUseCase
import com.parg3v.domain.use_cases.ValidateCyrillicTextUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AirlineTicketsModule {

    @Provides
    @Singleton
    fun provideSaveFromFieldToDataStoreUseCase(dataStoreRepository: DataStoreRepository): SaveFromFieldToDataStoreUseCase {
        return SaveFromFieldToDataStoreUseCase(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideGetFromFieldFromDataStoreUseCase(dataStoreRepository: DataStoreRepository): GetFromFieldFromDataStoreUseCase {
        return GetFromFieldFromDataStoreUseCase(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideValidateCyrillicTextUseCase(): ValidateCyrillicTextUseCase {
        return ValidateCyrillicTextUseCase()
    }

}
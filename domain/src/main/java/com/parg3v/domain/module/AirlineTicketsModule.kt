package com.parg3v.domain.module

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
    fun provideSaveFromFieldToDataStoreUseCase(): SaveFromFieldToDataStoreUseCase {
        return SaveFromFieldToDataStoreUseCase()
    }

    @Provides
    @Singleton
    fun provideGetFromFieldFromDataStoreUseCase(): GetFromFieldFromDataStoreUseCase {
        return GetFromFieldFromDataStoreUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateCyrillicTextUseCase(): ValidateCyrillicTextUseCase {
        return ValidateCyrillicTextUseCase()
    }

}
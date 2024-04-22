package com.parg3v.domain.module

import com.parg3v.domain.use_cases.GetFromFieldFromDataStore
import com.parg3v.domain.use_cases.SaveFromFieldToDataStore
import com.parg3v.domain.use_cases.ValidateCyrillicText
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
    fun provideSaveFromFieldToDataStore(): SaveFromFieldToDataStore {
        return SaveFromFieldToDataStore()
    }

    @Provides
    @Singleton
    fun provideGetFromFieldFromDataStore(): GetFromFieldFromDataStore {
        return GetFromFieldFromDataStore()
    }

    @Provides
    @Singleton
    fun provideValidateCyrillicText(): ValidateCyrillicText {
        return ValidateCyrillicText()
    }

}
package com.parg3v.data.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.parg3v.data.repository.DataStoreRepositoryImpl
import com.parg3v.data.repository.OffersRepositoryImpl
import com.parg3v.data.repository.TicketOffersRepositoryImpl
import com.parg3v.data.repository.TicketsRepositoryImpl
import com.parg3v.data.extensions.dataStore
import com.parg3v.domain.repository.DataStoreRepository
import com.parg3v.domain.repository.OffersRepository
import com.parg3v.domain.repository.TicketOffersRepository
import com.parg3v.domain.repository.TicketsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {

    @Binds
    @Singleton
    abstract fun bindOffersRepository(
        repository: OffersRepositoryImpl
    ): OffersRepository

    @Binds
    @Singleton
    abstract fun bindTicketOffersRepository(
        repository: TicketOffersRepositoryImpl
    ): TicketOffersRepository

    @Binds
    @Singleton
    abstract fun bindTicketsRepository(
        repository: TicketsRepositoryImpl
    ): TicketsRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        myUserPreferencesRepository: DataStoreRepositoryImpl
    ): DataStoreRepository

    companion object {

        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.dataStore
        }
    }
}
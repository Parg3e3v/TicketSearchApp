package com.parg3v.data.di.module

import com.parg3v.data.repository.OffersRepositoryImpl
import com.parg3v.data.repository.TicketOffersRepositoryImpl
import com.parg3v.domain.repository.OffersRepository
import com.parg3v.domain.repository.TicketOffersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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
}
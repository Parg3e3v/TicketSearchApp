package com.parg3v.domain.module

import com.parg3v.domain.repository.OffersRepository
import com.parg3v.domain.repository.TicketOffersRepository
import com.parg3v.domain.use_cases.GetAllOffersUseCase
import com.parg3v.domain.use_cases.GetAllTicketOffersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Provides
    @Singleton
    fun provideGetAllOffersUseCase(offersRepository: OffersRepository): GetAllOffersUseCase {
        return GetAllOffersUseCase(offersRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllTicketOffersUseCase(ticketOffersRepository: TicketOffersRepository): GetAllTicketOffersUseCase {
        return GetAllTicketOffersUseCase(ticketOffersRepository)
    }
}
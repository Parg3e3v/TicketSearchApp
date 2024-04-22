package com.parg3v.domain.module

import com.parg3v.domain.repository.OffersRepository
import com.parg3v.domain.use_cases.GetAllOffersUseCase
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
}
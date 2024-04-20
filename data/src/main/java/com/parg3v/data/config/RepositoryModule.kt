package com.parg3v.data.config

import com.parg3v.data.repository.OffersRepositoryImpl
import com.parg3v.domain.repository.OffersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOffersRepository(
        repository: OffersRepositoryImpl
    ): OffersRepository
}
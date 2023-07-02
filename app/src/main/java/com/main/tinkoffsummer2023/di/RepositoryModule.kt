package com.main.tinkoffsummer2023.di

import com.main.tinkoffsummer2023.data.MainRepositoryImpl
import com.main.tinkoffsummer2023.domain.MainRepository
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
    abstract fun bindAnimeRepository(
        animeRepositoryImpl: MainRepositoryImpl
    ): MainRepository

}

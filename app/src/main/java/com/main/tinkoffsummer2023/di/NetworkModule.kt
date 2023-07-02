package com.main.tinkoffsummer2023.di

import com.main.tinkoffsummer2023.core.Constants
import com.main.tinkoffsummer2023.data.remote.MainApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(Constants.BASE_URL)
            .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideApi(
        retrofit: Retrofit
    ): MainApi = retrofit.create(MainApi::class.java)
}

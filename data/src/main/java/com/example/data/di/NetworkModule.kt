package com.example.data.di

import com.example.core.network.RetrofitClient
import com.example.core.network.SummaryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSummaryApi(): SummaryApi {
        return RetrofitClient.summaryApi
    }
}
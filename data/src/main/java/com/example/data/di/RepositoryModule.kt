package com.example.data.di

import com.example.data.repository.RoomNoteRepository
import com.example.data.repository.SummaryRepositoryImpl
import com.example.domain.repository.NoteRepository
import com.example.domain.repository.SummaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract  class RepositoryModule{
    @Binds
    @Singleton
    abstract fun bindNoteRepository(
        implementation: RoomNoteRepository
    ): NoteRepository

    @Binds
    @Singleton
    abstract fun bindSummaryRepository(
        implementation: SummaryRepositoryImpl
    ): SummaryRepository

}
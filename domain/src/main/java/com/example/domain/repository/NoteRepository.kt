package com.example.domain.repository

import com.example.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun observeNotes() : Flow<List<Note>>
    suspend fun getNoteById(id: String) : Note?
    suspend fun saveNotes(note:Note)
    suspend fun deleteNote(id: String)
    suspend fun toggleFav(id: String)
}

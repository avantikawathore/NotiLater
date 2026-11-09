package com.example.data.repository

import com.example.data.local.NoteDao
import com.example.data.mapper.toDomain
import com.example.data.mapper.toEntity
import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomNoteRepository @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun observeNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getNoteById(id: String): Note? {
        return noteDao.getNoteById(id)?.toDomain()
    }

    override suspend fun saveNotes(note: Note) {
        noteDao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(id: String) {
        noteDao.deleteNote(id)
    }

    override suspend fun toggleFav(id: String) {
        noteDao.toggleFavorite(id)
    }
}
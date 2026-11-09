package com.example.data.repository

import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


class InMemoryNoteRepository @Inject constructor() : NoteRepository {

    private val notes = MutableStateFlow(
        listOf(
            Note(
                id = "1",
                title = "Kotlin Coroutines",
                content = "Asyncronouse",
                category = "Development",
                isFav = true
            ),
            Note(
                id = "2",
                title = "Clean Architeture",
                content = "Asyncroufewopdk ifoewope iodpo nouse",
                category = "Development"
            ),
            Note(
                id = "3",
                title = "Today's study plan",
                content = "Asyncro jkifoewp 9r0e0-23- 90-3-nouse",
                category = "Personal"
            )
        )
    )

    override fun observeNotes(): Flow<List<Note>> = notes.asStateFlow()

    override suspend fun getNoteById(id: String): Note? =
        notes.value.find { it.id == id }


    override suspend fun saveNotes(note: Note) {
        notes.update { current ->
            val existingIndex = current.indexOfFirst { it.id == note.id }
            if (existingIndex == -1) {
                listOf(note) + current
            } else {
                current.map { existing ->
                    if (existing.id == note.id) note else existing
                }
            }

        }
    }

    override suspend fun deleteNote(id: String) {
        notes.update { current ->
            current.filterNot { it.id == id }
        }
    }

    override suspend fun toggleFav(id: String) {
        notes.update { current ->
            current.map { note ->
                if (note.id == id) {
                    note.copy(isFav = !note.isFav)
                } else {
                    note
                }
            }
        }
    }
}
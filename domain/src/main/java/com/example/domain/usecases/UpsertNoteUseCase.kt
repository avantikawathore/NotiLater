package com.example.domain.usecases

import com.example.domain.model.Note
import java.util.UUID
import javax.inject.Inject

private const val DEFAULT_CATEGORY = "General"

class UpsertNoteUseCase @Inject constructor(
    private val getNoteById: GetNoteByIdUseCase,
    private val saveNote: SaveNotesUseCase
) {
    suspend operator fun invoke(
        noteId: String?,
        title: String,
        content: String,
        category: String
    ): Note {
        val now = System.currentTimeMillis()
        val cleanTitle = title.trim()
        val cleanContent = content.trim()
        val cleanCategory = category.trim().ifBlank { DEFAULT_CATEGORY }

        val note = if (noteId == null) {
            Note(
                id = UUID.randomUUID().toString(),
                title = cleanTitle,
                content = cleanContent,
                category = cleanCategory,
                isFav = false,
                createdAt = now,
                updatedAt = now
            )
        } else {
            val existing = getNoteById(noteId) ?: error("Note not found")
            existing.copy(
                title = cleanTitle,
                content = cleanContent,
                category = cleanCategory,
                updatedAt = now
            )
        }

        saveNote(note)
        return note
    }
}
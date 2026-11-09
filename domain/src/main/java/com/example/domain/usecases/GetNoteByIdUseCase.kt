package com.example.domain.usecases

import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: String): Note? {
        return repository.getNoteById(id)
    }
}
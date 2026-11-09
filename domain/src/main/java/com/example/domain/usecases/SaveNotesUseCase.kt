package com.example.domain.usecases

import com.example.domain.model.Note
import com.example.domain.repository.NoteRepository
import javax.inject.Inject

class SaveNotesUseCase @Inject constructor(
    private val repository: NoteRepository
){
    suspend operator fun invoke(note: Note){
        repository.saveNotes(note)
    }
}
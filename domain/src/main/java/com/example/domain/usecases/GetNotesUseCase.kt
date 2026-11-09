package com.example.domain.usecases

import com.example.domain.repository.NoteRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private  val repository: NoteRepository
) {

    operator fun invoke() = repository.observeNotes()
}
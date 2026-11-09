package com.example.domain.usecases

import com.example.domain.repository.NoteRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(noteId: String) {
        repository.toggleFav(noteId)
    }
}
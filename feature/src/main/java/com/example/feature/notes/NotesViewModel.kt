package com.example.feature.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.DeleteNoteUseCase
import com.example.domain.usecases.GetNotesUseCase
import com.example.domain.usecases.ToggleFavoriteUseCase
import com.example.domain.usecases.UpsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotes: GetNotesUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase,
    private val upsertNote: UpsertNoteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<NotesEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        observeNotes()
    }

    fun onIntent(intent: NotesIntent) {
        when (intent) {
            is NotesIntent.SearchChange -> _uiState.update {
                it.copy(searchQuery = intent.query)
            }

            is NotesIntent.FilterSelected -> _uiState.update {
                it.copy(
                    filter = intent.filter,
                    sort = if (intent.filter == NoteFilter.Recent) NoteSort.Default else it.sort
                )
            }

            is NotesIntent.SortSelected -> _uiState.update {
                it.copy(
                    sort = intent.sort,
                    filter = if (intent.sort == NoteSort.Alphabetical && it.filter == NoteFilter.Recent) {
                        NoteFilter.All
                    } else {
                        it.filter
                    }
                )
            }

            is NotesIntent.SelectNote -> _uiState.update {
                it.copy(selectedNoteId = intent.noteId)
            }

            is NotesIntent.ToggleFavorite -> launchCatching("Unable to update favorite.") {
                toggleFavorite(intent.noteId)
            }

            is NotesIntent.DeleteNote -> launchCatching("Unable to delete note.") {
                deleteNote(intent.noteId)
            }

            is NotesIntent.SaveNote -> launchCatching("Unable to save note.") {
                upsertNote(
                    noteId = intent.noteId,
                    title = intent.title,
                    content = intent.content,
                    category = intent.category
                )
                _effect.send(NotesEffect.NoteSaved)
            }
        }
    }

    private fun observeNotes() {
        getNotes()
            .onEach { notes ->
                _uiState.update {
                    it.copy(notes = notes, isLoading = false, errorMessage = null)
                }
            }
            .catch { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Unable to load notes."
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun launchCatching(
        fallbackError: String,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch {
            try {
                block()
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _effect.send(NotesEffect.ShowError(exception.message ?: fallbackError))
            }
        }
    }
}
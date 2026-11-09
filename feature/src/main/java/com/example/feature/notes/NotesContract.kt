package com.example.feature.notes

import com.example.domain.model.Note

sealed interface NoteFilter {
    val label: String

    data object All : NoteFilter { override val label = "All Notes" }
    data object Recent : NoteFilter { override val label = "Recent" }
    data object Favorites : NoteFilter { override val label = "Favorites" }
    data class Category(val name: String) : NoteFilter {
        override val label: String get() = name
    }
}

enum class NoteSort { Default, Alphabetical }

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val searchQuery: String = "",
    val filter: NoteFilter = NoteFilter.All,
    val sort: NoteSort = NoteSort.Default,
    val selectedNoteId: String? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
) {
    val selectedNote: Note?
        get() = selectedNoteId?.let { id -> notes.firstOrNull { it.id == id } }

    val categories: List<String>
        get() = notes.map { it.category }.filter { it.isNotBlank() }.distinct().sorted()

    val visibleNotes: List<Note>
        get() {
            val query = searchQuery.trim()
            val matching = notes.filter { note ->
                val matchesQuery = query.isEmpty() ||
                        note.title.contains(query, ignoreCase = true) ||
                        note.content.contains(query, ignoreCase = true)

                val matchesFilter = when (filter) {
                    NoteFilter.All, NoteFilter.Recent -> true
                    NoteFilter.Favorites -> note.isFav
                    is NoteFilter.Category -> note.category == filter.name
                }
                matchesQuery && matchesFilter
            }

            return when {
                filter == NoteFilter.Recent -> matching.sortedByDescending { it.updatedAt }
                sort == NoteSort.Alphabetical -> matching.sortedBy { it.title.lowercase() }
                else -> matching
            }
        }
}

sealed interface NotesIntent {
    data class SearchChange(val query: String) : NotesIntent
    data class FilterSelected(val filter: NoteFilter) : NotesIntent
    data class SortSelected(val sort: NoteSort) : NotesIntent
    data class SelectNote(val noteId: String) : NotesIntent
    data class ToggleFavorite(val noteId: String) : NotesIntent
    data class DeleteNote(val noteId: String) : NotesIntent
    data class SaveNote(
        val title: String,
        val content: String,
        val category: String,
        val noteId: String? = null
    ) : NotesIntent
}

sealed interface NotesEffect {
    data object NoteSaved : NotesEffect
    data class ShowError(val message: String) : NotesEffect
}
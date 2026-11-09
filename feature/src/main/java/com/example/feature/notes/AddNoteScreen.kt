package com.example.feature.notes

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feature.components.NoteEditorContent
import kotlinx.coroutines.launch

private const val DEFAULT_CATEGORY = "General"

@Composable
fun AddNoteScreen(
    viewModel: NotesViewModel= hiltViewModel(),
    onBackClick: () -> Unit,
    noteId: String? = null
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val currentOnBack by rememberUpdatedState(onBackClick)

    var title by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf(DEFAULT_CATEGORY) }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                NotesEffect.NoteSaved -> currentOnBack()
                is NotesEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }
    
    val noteToEdit = uiState.selectedNote?.takeIf { noteId != null && it.id == noteId }
    LaunchedEffect(noteToEdit?.id) {
        noteToEdit?.let {
            title = it.title
            content = it.content
            category = it.category
        }
    }

    NoteEditorContent(
        isEditing = noteId != null,
        title = title,
        content = content,
        category = category,
        snackbarHostState = snackbarHostState,
        onTitleChange = { title = it },
        onContentChange = { content = it },
        onCategoryChange = { category = it },
        onBackClick = onBackClick,
        onSaveClick = {
            if (title.isNotBlank() && content.isNotBlank()) {
                viewModel.onIntent(
                    NotesIntent.SaveNote(
                        title = title,
                        content = content,
                        category = category,
                        noteId = noteId
                    )
                )
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar("Please enter a title and content.")
                }
            }
        }
    )
}
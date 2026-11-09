package com.example.feature.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feature.R
import com.example.feature.components.NoteCards
import com.example.feature.components.NoteFilterRow
import com.example.feature.components.NotesHeader
import com.example.feature.components.NotesSearchBar
import com.example.feature.components.NotesTopBar

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel(),
    onAddNoteClick: () -> Unit = {},
    onNoteClick: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NotesContent(
        uiState = uiState,
        onIntent = viewModel::onIntent,
        onAddNoteClick = onAddNoteClick,
        onNoteClick = onNoteClick
    )
}

@Composable
private fun NotesContent(
    uiState: NotesUiState,
    onIntent: (NotesIntent) -> Unit,
    onAddNoteClick: () -> Unit,
    onNoteClick: (String) -> Unit
) {
    val visibleNotes = remember(
        uiState.notes, uiState.searchQuery, uiState.filter, uiState.sort
    ) { uiState.visibleNotes }

    val filters = remember(uiState.notes) {
        listOf(NoteFilter.All, NoteFilter.Recent, NoteFilter.Favorites) +
                uiState.categories.map { NoteFilter.Category(it) }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            NotesTopBar(
                onLastModifiedClick = { onIntent(NotesIntent.FilterSelected(NoteFilter.Recent)) },
                onAlphabeticalClick = { onIntent(NotesIntent.SortSelected(NoteSort.Alphabetical)) }
            )
        },
        floatingActionButton = { AddNoteFab(onClick = onAddNoteClick) }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(12.dp))
            NotesHeader(noteCount = uiState.notes.size)

            Spacer(Modifier.height(20.dp))
            NotesSearchBar(
                value = uiState.searchQuery,
                onValueChange = { onIntent(NotesIntent.SearchChange(it)) }
            )

            Spacer(Modifier.height(14.dp))
            NoteFilterRow(
                filters = filters.map { it.label },
                selectedFilter = uiState.filter.label,
                onFilterSelect = { label ->
                    filters.firstOrNull { it.label == label }
                        ?.let { onIntent(NotesIntent.FilterSelected(it)) }
                }
            )

            Spacer(Modifier.height(18.dp))
            NotesBody(
                uiState = uiState,
                visibleNotes = visibleNotes,
                onIntent = onIntent,
                onNoteClick = onNoteClick
            )
        }
    }
}

@Composable
private fun ColumnScope.NotesBody(
    uiState: NotesUiState,
    visibleNotes: List<com.example.domain.model.Note>,
    onIntent: (NotesIntent) -> Unit,
    onNoteClick: (String) -> Unit
) {
    val error = uiState.errorMessage

    when {
        uiState.isLoading -> CenteredContent { CircularProgressIndicator() }

        error != null -> CenteredContent {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        visibleNotes.isEmpty() -> CenteredContent {
            Text(
                text = if (uiState.filter == NoteFilter.Favorites) {
                    stringResource(R.string.no_favorite_notes_yet)
                } else {
                    stringResource(R.string.no_notes_found_try_another_filter_or_add_a_note)
                },
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        else -> LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(top = 2.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(items = visibleNotes, key = { it.id }) { note ->
                NoteCards(
                    note = note,
                    onClick = { onNoteClick(note.id) },
                    onFavoriteClick = { onIntent(NotesIntent.ToggleFavorite(note.id)) },
                    onDeleteClick = { onIntent(NotesIntent.DeleteNote(note.id)) }
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.CenteredContent(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun AddNoteFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = RoundedCornerShape(17.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_note))
    }
}
package com.example.feature.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feature.components.ErrorCard
import com.example.feature.components.LoadingCard
import com.example.feature.components.SummaryHeader
import com.example.feature.components.SummaryResultCard
import com.example.feature.components.SummaryTopBar

@Composable
fun SummaryScreen(
    noteTitle: String,
    noteContent: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SummaryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(noteTitle, noteContent) {
        viewModel.onEvent(SummaryUiEvent.Load(noteTitle, noteContent))
    }

    SummaryContent(
        noteTitle = noteTitle,
        uiState = uiState,
        onBackClick = onBackClick,
        onRetryClick = { viewModel.onEvent(SummaryUiEvent.Retry) },
        modifier = modifier
    )
}

@Composable
private fun SummaryContent(
    noteTitle: String,
    uiState: SummaryUiState,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { SummaryTopBar(onBackClick = onBackClick) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SummaryHeader(noteTitle = noteTitle)

            val error = uiState.errorMessage
            val summary = uiState.summary

            when {
                error != null -> ErrorCard(
                    message = error,
                    retryEnabled = !uiState.isLoading,
                    onRetryClick = onRetryClick
                )

                summary != null -> SummaryResultCard(summary = summary)

                else -> LoadingCard()
            }
        }
    }
}
package com.example.feature.summary

data class SummaryUiState(
    val noteText: String = "",
    val summary: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface SummaryUiEvent {
    data class Load(val title: String, val content: String) : SummaryUiEvent
    data object Retry : SummaryUiEvent
}
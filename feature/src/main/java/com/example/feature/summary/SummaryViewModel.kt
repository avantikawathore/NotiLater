package com.example.feature.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GenerateSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val generateSummaryUseCase: GenerateSummaryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SummaryUiState())
    val uiState = _uiState.asStateFlow()

    private var summaryJob: Job? = null

    fun onEvent(event: SummaryUiEvent) {
        when (event) {
            is SummaryUiEvent.Load -> load(event.title, event.content)
            SummaryUiEvent.Retry -> generateSummary()
        }
    }

    private fun load(title: String, content: String) {
        val text = if (title.isBlank()) content else "$title\n\n$content"
        val state = _uiState.value

        val alreadyHandled = text == state.noteText &&
                (state.summary != null || state.isLoading)
        if (alreadyHandled) return

        _uiState.update { it.copy(noteText = text, summary = null, errorMessage = null) }
        generateSummary()
    }

    private fun generateSummary() {
        val noteText = _uiState.value.noteText.trim()

        if (noteText.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Please enter some text to summarize.") }
            return
        }

        summaryJob?.cancel()
        summaryJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, summary = null, errorMessage = null) }

            try {
                val result = generateSummaryUseCase(noteText)
                _uiState.update { it.copy(isLoading = false, summary = result.summary) }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = exception.message
                            ?: "Unable to generate a summary. Please try again."
                    )
                }
            }
        }
    }
}
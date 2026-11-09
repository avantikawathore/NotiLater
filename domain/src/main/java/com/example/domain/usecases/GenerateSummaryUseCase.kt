package com.example.domain.usecases

import com.example.domain.repository.SummaryRepository
import javax.inject.Inject

class GenerateSummaryUseCase @Inject constructor(
    private val repository: SummaryRepository
) {
    suspend operator fun invoke(noteText: String) =
        repository.generateSummary(noteText)
}
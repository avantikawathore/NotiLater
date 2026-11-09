package com.example.data.repository

import com.example.core.network.SummaryApi
import com.example.core.network.model.SummaryRequest
import com.example.domain.model.Summary
import com.example.domain.repository.SummaryRepository
import javax.inject.Inject

class SummaryRepositoryImpl @Inject constructor(
    private val summaryApi: SummaryApi
) : SummaryRepository {

    override suspend fun generateSummary(
        noteText: String
    ): Summary {
        val response = summaryApi.generateSummary(
            SummaryRequest(noteText = noteText)
        )

        return Summary(
            summary = response.summary,
            keyPoints = response.keyPoints
        )
    }
}
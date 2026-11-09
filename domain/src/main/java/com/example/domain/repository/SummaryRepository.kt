package com.example.domain.repository

import com.example.domain.model.Summary

interface SummaryRepository {
    suspend fun generateSummary(noteText: String): Summary
}
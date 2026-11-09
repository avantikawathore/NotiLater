package com.example.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SummaryResponse(
    val summary: String,
    val keyPoints: List<String>
)
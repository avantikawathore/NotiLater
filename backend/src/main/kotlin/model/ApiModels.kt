package model

import kotlinx.serialization.Serializable


@Serializable
data class HealthResponse(
    val status: String,
    val message: String
)

@Serializable
data class ErrorResponse(
    val error: String
)

@Serializable
data class SummaryRequest(
    val noteText: String
)

@Serializable
data class SummaryResponse(
    val summary: String,
    val keyPoints: List<String>
)
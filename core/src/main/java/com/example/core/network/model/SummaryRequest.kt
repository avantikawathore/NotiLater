package com.example.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SummaryRequest(
    val noteText: String
)
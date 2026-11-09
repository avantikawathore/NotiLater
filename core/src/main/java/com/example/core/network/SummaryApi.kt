package com.example.core.network

import com.example.core.network.model.SummaryRequest
import com.example.core.network.model.SummaryResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SummaryApi {

    @POST("api/summary")
    suspend fun generateSummary(
        @Body request: SummaryRequest
    ): SummaryResponse
}
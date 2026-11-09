package com.example

import gemini.GeminiClient
import gemini.GeminiConfig
import plugins.configureErrorHandling
import routes.aiRoutes
import routes.healthRoutes
import service.NoteAiService
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing


fun Application.configureRouting() {
    install(ContentNegotiation) {
        json()
    }
    configureErrorHandling()

    val config = GeminiConfig.fromEnvironment()
    if (config.apiKey.isNullOrBlank()) {
        log.warn("GEMINI_API_KEY is not set. AI endpoints will fail until it is configured.")
    }

    val gemini = GeminiClient(config)
    val aiService = NoteAiService(gemini)

    monitor.subscribe(ApplicationStopped) { gemini.close() }

    routing {
        healthRoutes()
        aiRoutes(aiService)
    }
}
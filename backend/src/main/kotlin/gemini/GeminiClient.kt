package gemini

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal val GeminiJson = Json { ignoreUnknownKeys = true }

class GeminiClient(
    private val config: GeminiConfig,
    private val http: HttpClient = defaultHttpClient()
) : AutoCloseable {

    suspend fun generateText(prompt: String): String {
        val apiKey = config.apiKey
        if (apiKey.isNullOrBlank()) {
            error("GEMINI_API_KEY environment variable is missing.")
        }

        val response = http.post(config.endpoint) {
            header("x-goog-api-key", apiKey)
            contentType(ContentType.Application.Json)
            setBody(
                GeminiRequest(
                    contents = listOf(GeminiContent(parts = listOf(GeminiPart(text = prompt)))),
                    generationConfig = GeminiGenerationConfig(responseMimeType = "application/json")
                )
            )
        }

        val body = response.bodyAsText()

        if (!response.status.isSuccess()) {
            error("Gemini API request failed with HTTP ${response.status.value}: $body")
        }

        return GeminiJson.decodeFromString<GeminiResponse>(body)
            .candidates
            .firstOrNull()
            ?.content
            ?.parts
            ?.mapNotNull { it.text }
            ?.joinToString("\n")
            ?.takeIf { it.isNotBlank() }
            ?: error("Gemini returned an empty response.")
    }

    override fun close() = http.close()

    private companion object {
        fun defaultHttpClient() = HttpClient(CIO) {
            install(ContentNegotiation) { json(GeminiJson) }
            install(HttpTimeout) {
                requestTimeoutMillis = 30_000
                connectTimeoutMillis = 15_000
                socketTimeoutMillis = 30_000
            }
        }
    }
}
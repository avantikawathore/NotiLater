package gemini

import kotlinx.serialization.Serializable


@Serializable
internal data class GeminiRequest(
    val contents: List<GeminiContent>,
    val generationConfig: GeminiGenerationConfig
)

@Serializable
internal data class GeminiGenerationConfig(
    val responseMimeType: String
)

@Serializable
internal data class GeminiContent(
    val parts: List<GeminiPart>
)

@Serializable
internal data class GeminiPart(
    val text: String? = null
)

@Serializable
internal data class GeminiResponse(
    val candidates: List<GeminiCandidate> = emptyList()
)

@Serializable
internal data class GeminiCandidate(
    val content: GeminiContent? = null
)
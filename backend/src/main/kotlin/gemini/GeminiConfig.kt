package gemini

private const val DEFAULT_MODEL = "gemini-3.6-flash"

data class GeminiConfig(
    val apiKey: String?,
    val model: String = DEFAULT_MODEL
) {
    val endpoint: String
        get() = "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent"

    companion object {
        fun fromEnvironment() = GeminiConfig(
            apiKey = System.getenv("GEMINI_API_KEY"),
            model = System.getenv("GEMINI_MODEL")?.takeIf { it.isNotBlank() } ?: DEFAULT_MODEL
        )
    }
}
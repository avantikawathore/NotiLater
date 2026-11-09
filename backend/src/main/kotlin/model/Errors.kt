package model

class ValidationException(message: String) : RuntimeException(message)

class AiGenerationException(
    val userMessage: String,
    cause: Throwable
) : RuntimeException(userMessage, cause)
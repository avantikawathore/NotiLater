package service

import gemini.GeminiClient
import gemini.GeminiJson
import model.AiGenerationException
import model.SummaryResponse
import kotlinx.coroutines.CancellationException

class NoteAiService(private val gemini: GeminiClient) {

    suspend fun generateSummary(noteText: String): SummaryResponse =
        aiCall("Unable to generate summary right now.") {
            generate<SummaryResponse>(Prompts.summary(noteText))
        }

    private suspend inline fun <reified T> generate(prompt: String): T =
        GeminiJson.decodeFromString<T>(gemini.generateText(prompt).stripCodeFences())

    private suspend fun <T> aiCall(userMessage: String, block: suspend () -> T): T =
        try {
            block()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            throw AiGenerationException(userMessage, e)
        }
}


private fun String.stripCodeFences(): String =
    trim().removePrefix("```json").removePrefix("```").removeSuffix("```").trim()

package plugins

import model.AiGenerationException
import model.ErrorResponse
import model.ValidationException
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.ContentConvertException
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCallPipeline
import io.ktor.server.application.call
import io.ktor.server.application.log
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.response.respond
import kotlinx.coroutines.CancellationException

fun Application.configureErrorHandling() {
    intercept(ApplicationCallPipeline.Call) {
        try {
            proceed()
        } catch (cause: CancellationException) {
            throw cause
        } catch (cause: Throwable) {
            val (status, message) = when (cause) {
                is ValidationException ->
                    HttpStatusCode.BadRequest to cause.message.orEmpty()

                is BadRequestException, is ContentConvertException ->
                    HttpStatusCode.BadRequest to "Invalid request body."

                is AiGenerationException -> {
                    call.application.log.error("AI generation failed", cause)
                    HttpStatusCode.InternalServerError to cause.userMessage
                }

                else -> {
                    call.application.log.error("Unhandled error", cause)
                    HttpStatusCode.InternalServerError to "Something went wrong. Please try again."
                }
            }

            call.respond(status, ErrorResponse(message))
        }
    }
}
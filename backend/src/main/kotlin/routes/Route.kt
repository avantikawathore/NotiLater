package routes

import model.HealthResponse
import model.SummaryRequest
import model.validated
import service.NoteAiService
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.healthRoutes() {
    get("/") {
        call.respondText("NotiLater Backend")
    }

    get("/health") {
        call.respond(
            HealthResponse(
                status = "OK",
                message = "NotiLater backend is running"
            )
        )
    }
}

fun Route.aiRoutes(ai: NoteAiService) {
    route("/api") {

        post("/summary") {
            val request = call.receive<SummaryRequest>().validated()
            call.respond(ai.generateSummary(request.noteText))
        }
    }
}
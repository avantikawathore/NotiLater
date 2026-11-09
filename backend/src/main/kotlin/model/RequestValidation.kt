package model

private const val MAX_NOTE_LENGTH = 20_000
private const val MIN_QUESTIONS = 1
private const val MAX_QUESTIONS = 10

fun SummaryRequest.validated(): SummaryRequest {
    if (noteText.isBlank()) {
        throw ValidationException("Please provide some note text.")
    }
    if (noteText.length > MAX_NOTE_LENGTH) {
        throw ValidationException("Note text is too long.")
    }
    return this
}
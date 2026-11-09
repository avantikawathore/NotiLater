package com.example.notilater.navigation

object Routes {

    const val NOTES = "notes"
    const val ADD_NOTE = "add_note"
    const val NOTE_DETAILS = "note_details"
    const val EDIT_NOTE = "edit_note/{noteId}"
    const val SUMMARY = "summary"

    fun editNote(noteId: String) =
        "edit_note/${android.net.Uri.encode(noteId)}"
}
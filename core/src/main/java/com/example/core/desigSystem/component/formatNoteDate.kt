package com.example.core.desigSystem.component

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

fun formatNoteDate(timestamp: Long): String {
    val formatter = SimpleDateFormat(
        "MMM d, h:mm a",
        Locale.getDefault()
    )
    return formatter.format(Date(timestamp))
}
package com.example.data.mapper

import com.example.data.local.NoteEntity
import com.example.domain.model.Note


fun NoteEntity.toDomain(): Note {

    return Note(
        id = id,
        title = title,
        content = content,
        category = category,
        isFav = isFav,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        category = category,
        isFav = isFav,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
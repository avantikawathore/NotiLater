package com.example.domain.model

data class  Note(
    val id: String,
    val title: String,
    val content: String,
    val category: String,
    val isFav: Boolean= false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
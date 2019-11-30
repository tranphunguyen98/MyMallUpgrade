package com.example.mymallupgrade.domain.entity.movie

data class ReviewEntity(
    val id: String,
    val author: String,
    val content: String? = null
)
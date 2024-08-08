package com.onboarding.practice.post.dto

import java.time.LocalDateTime

data class PostResponse (
    val id: Long?,
    val title: String?,
    val content: String?,
    val createdBy: String,
    val updatedBy: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
)
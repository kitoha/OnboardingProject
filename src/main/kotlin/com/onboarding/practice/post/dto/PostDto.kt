package com.onboarding.practice.post.dto

import jakarta.validation.constraints.Size

data class PostDto(
    @field:Size(max = 200, message = "제목은 200자 이하로 입력해주세요.")
    val title:String?,
    @field:Size(max = 1000, message = "내용은 1000자 이하로 입력해주세요.")
    val content: String?
)

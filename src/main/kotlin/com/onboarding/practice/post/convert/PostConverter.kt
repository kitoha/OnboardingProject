package com.onboarding.practice.post.convert

import com.onboarding.practice.post.dto.PostResponse
import com.onboarding.practice.post.entity.Post
import lombok.AccessLevel
import lombok.NoArgsConstructor

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PostConverter {
    companion object {
        fun convertToPostResponse(post: Post): PostResponse {
            return PostResponse(
                post.id,
                post.title,
                post.content,
                post.createdBy,
                post.updatedBy,
                post.createdAt,
                post.updateAt
            )
        }
    }
}
package com.onboarding.practice.post.service

import com.onboarding.practice.common.logger.logger
import com.onboarding.practice.post.dto.CommentDto
import com.onboarding.practice.post.entity.Comment
import com.onboarding.practice.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class CommentService(
    private val postRepository: PostRepository
) {

    val log = logger()
    /**
     * 댓글 추가 함수
     */
    fun addComment(commentDto: CommentDto, userName:String){
        val post = postRepository.findByIdOrNull(commentDto.postId) ?: throw IllegalStateException("Post not Found")

        try {
            post.comments += Comment(
                null,
                commentDto.content,
                userName,
                userName,
                post
            )

            postRepository.save(post)
        }catch (e: Exception){
            log.error("[CommentService] - [addComment] failed add comment",e)
        }
    }

}
package com.onboarding.practice.post.service

import com.onboarding.practice.common.Logger.logger
import com.onboarding.practice.post.convert.PostConverter
import com.onboarding.practice.post.dto.PostDto
import com.onboarding.practice.post.dto.PostResponse
import com.onboarding.practice.post.entity.Post
import com.onboarding.practice.post.repository.PostRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


@Slf4j
@Service
class PostService (
    private val postRepository: PostRepository
){
    val log = logger()
    fun createPost(postDto: PostDto,userName:String):Boolean{

        try {
            val post = Post(
                null,
                postDto.title,
                postDto.content,
                userName,
                userName,
            )

            postRepository.save(post)

            return true
        }catch (e: Exception){
            log.error("[PostService] - [createPost] failed createPost",e)
            return false
        }
    }

    fun updatePost(id:Long, request: PostDto,userName:String):Boolean{

        val post = postRepository.findByIdOrNull(id) ?: throw IllegalStateException("Post not Found")

        if (!isWithinEditPeriod(post.createdAt)) {
            return false
        }

        try {

            val updatedPost = Post(
                null,
                title = request.title ?: post.title,
                content = request.content ?: post.content,
                createdBy = post.createdBy,
                updatedBy = userName,
            )

            postRepository.save(updatedPost)

            return true
        }catch (e: Exception) {
            log.error("[PostService] - [updatePost] failed update post", e)
            return false
        }
    }

    fun getPostList(pageable: Pageable):List<PostResponse>{

        val postList : List<Post> = postRepository.findAll(pageable).content

        return postList.stream().map { PostConverter.convertToPostResponse(it) }.toList()
    }

    fun deletePost(id: Long):Boolean{
        val post = postRepository.findByIdOrNull(id) ?: throw IllegalStateException("Post not Found")

        post.deleted= true

        postRepository.save(post)

        return true
    }

    fun getRemainingEditDays(id: Long) : Long{
        val post = postRepository.findByIdOrNull(id) ?: throw IllegalStateException("Post not Found")

        val deadlineDate = post.createdAt?.let { calculateEditDeadline(it) }
        val currentDate = LocalDateTime.now()

        return if (deadlineDate != null && isWithinEditPeriod(post.createdAt)) {
            ChronoUnit.DAYS.between(currentDate, deadlineDate)
        } else {
            0L
        }
    }

    /**
     * 게시글 수정일 계산 함수
     */
    private fun calculateEditDeadline(createdAt: LocalDateTime): LocalDateTime {
        return createdAt.plusDays(10L)
    }

    /**
     * 게시글 수정가능 판단 함수
     */
    private fun isWithinEditPeriod(createdAt: LocalDateTime?): Boolean {
        val deadlineDate = createdAt?.let { calculateEditDeadline(it) } ?: return false
        val currentDate = LocalDateTime.now()
        return currentDate.isBefore(deadlineDate) || currentDate.isEqual(deadlineDate)
    }
}
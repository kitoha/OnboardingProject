package com.onboarding.practice.post.service

import com.onboarding.practice.post.dto.PostDto
import com.onboarding.practice.post.entity.Post
import com.onboarding.practice.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService (
    private val postRepository: PostRepository
){

    fun createPost(postDto: PostDto,userName:String):String{

        val post = Post(
            null,
            postDto.title,
            postDto.content,
            userName,
            userName,
        )

        postRepository.save(post)

        return "게시글 생성이 완료되었습니다."
    }

    fun updatePost(id:Long, request: PostDto,userName:String):String{

        val post = postRepository.findByIdOrNull(id) ?: throw IllegalStateException("Post not Found")

        val updatedPost = Post(
            null,
            title = request.title?: post.title,
            content = request.content?: post.content,
            createdBy = post.createdBy,
            updatedBy =  userName,
        )

        postRepository.save(updatedPost)

        return "게시글 수정이 완료되었습니다."

    }
}
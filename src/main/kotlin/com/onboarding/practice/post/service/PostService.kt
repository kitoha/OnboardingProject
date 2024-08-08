package com.onboarding.practice.post.service

import com.onboarding.practice.post.convert.PostConverter
import com.onboarding.practice.post.dto.PostDto
import com.onboarding.practice.post.dto.PostResponse
import com.onboarding.practice.post.entity.Post
import com.onboarding.practice.post.repository.PostRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    fun getPostList(pageable: Pageable):List<PostResponse>{

        val postList : List<Post> = postRepository.findAll(pageable).content

        return postList.stream().map { PostConverter.convertToPostResponse(it) }.toList()
    }

    fun deletePost(id: Long):String{
        val post = postRepository.findByIdOrNull(id) ?: throw IllegalStateException("Post not Found")

        post.deleted= true

        postRepository.save(post)

        return "삭제 완료"
    }
}
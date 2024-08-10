package com.onboarding.practice.post.controller

import com.onboarding.practice.post.dto.PostDto
import com.onboarding.practice.post.dto.PostResponse
import com.onboarding.practice.post.service.PostService
import org.springframework.data.domain.Pageable
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController (
    private val postService: PostService
){

    @PostMapping("/api/post")
    fun createPost(@RequestBody postDto: PostDto, @AuthenticationPrincipal user:User):String{

        val response: String = postService.createPost(postDto,user.username)

        return response
    }

    @PostMapping("/api/post/{id}")
    fun updatePost(@PathVariable id:Long, @RequestBody postDto: PostDto, @AuthenticationPrincipal user:User):String{

        val response : String = postService.updatePost(id,postDto,user.username)

        return response
    }

    @GetMapping("/api/post/list")
    fun findPostList(pageable: Pageable) : List<PostResponse>{
        return postService.getPostList(pageable)
    }

    @DeleteMapping("/api/post/{id}")
    fun deletedPost(@PathVariable id:Long): String{
        return postService.deletePost(id)
    }

    @GetMapping("/api/post/remainDay/{id}")
    fun getRemainingEditDays(@PathVariable id:Long): Long{
        return postService.getRemainingEditDays(id)
    }
}

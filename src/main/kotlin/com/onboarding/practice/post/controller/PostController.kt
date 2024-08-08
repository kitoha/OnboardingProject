package com.onboarding.practice.post.controller

import com.onboarding.practice.post.dto.PostDto
import com.onboarding.practice.post.service.PostService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
}
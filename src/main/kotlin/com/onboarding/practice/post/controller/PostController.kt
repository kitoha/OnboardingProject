package com.onboarding.practice.post.controller

import com.onboarding.practice.common.dto.BaseResponse
import com.onboarding.practice.common.enums.ResultCode
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
    fun createPost(@RequestBody postDto: PostDto, @AuthenticationPrincipal user:User):BaseResponse<Boolean>{

        val response: Boolean = postService.createPost(postDto,user.username)

        return BaseResponse(ResultCode.SUCCESS,response,"게시글 생성이 완료되었습니다.")
    }

    @PostMapping("/api/post/{id}")
    fun updatePost(@PathVariable id:Long, @RequestBody postDto: PostDto, @AuthenticationPrincipal user:User):BaseResponse<Boolean>{

        val response : Boolean = postService.updatePost(id,postDto,user.username)

        return BaseResponse(ResultCode.SUCCESS,response, "게시글 수정이 완료되었습니다.")
    }

    @GetMapping("/api/post/list")
    fun findPostList(pageable: Pageable) : BaseResponse<List<PostResponse>>{
        val postResponse :List<PostResponse> = postService.getPostList(pageable)
        return BaseResponse(ResultCode.SUCCESS,postResponse,"게시글 목록 조회 완료")
    }

    @DeleteMapping("/api/post/{id}")
    fun deletedPost(@PathVariable id:Long): BaseResponse<Boolean>{
        val response: Boolean = postService.deletePost(id)
        return BaseResponse(ResultCode.SUCCESS,response, "게시물 삭제가 완료되었습니다.")
    }

    @GetMapping("/api/post/remainDay/{id}")
    fun getRemainingEditDays(@PathVariable id:Long): BaseResponse<Long>{
        val remainDay =  postService.getRemainingEditDays(id)
        return BaseResponse(ResultCode.SUCCESS,remainDay,"수정 가능일 조회 완료")
    }
}

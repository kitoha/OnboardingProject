package com.onboarding.practice.post.controller

import com.onboarding.practice.common.dto.BaseResponse
import com.onboarding.practice.common.enums.ResultCode
import com.onboarding.practice.post.dto.CommentDto
import com.onboarding.practice.post.service.CommentService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    private val commentService: CommentService
) {

    @RequestMapping("/api/v1/comment")
    fun addComment(@RequestBody commentDto: CommentDto, @AuthenticationPrincipal user: User):BaseResponse<String>{
        commentService.addComment(commentDto,user.username)
        return BaseResponse(ResultCode.SUCCESS,null,"댓글 생성 완료")
    }
}
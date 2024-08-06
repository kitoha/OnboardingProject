package com.onboarding.practice.member.controller

import com.onboarding.practice.common.dto.BaseResponse
import com.onboarding.practice.common.enums.ResultCode
import com.onboarding.practice.member.dto.LoginRequest
import com.onboarding.practice.member.dto.LoginResponse
import com.onboarding.practice.member.dto.MemberDto
import com.onboarding.practice.member.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {

    /***
     * 회원 가입
     */
    @PostMapping("/api/v1/signup")
    fun signUp(@RequestBody memberDto: MemberDto): BaseResponse<String>{
        return BaseResponse(ResultCode.SUCCESS, null, memberService.signUp(memberDto))
    }

    /***
     * 로그인
     */
    @PostMapping("/api/v1/login")
    fun login(@RequestBody loginRequest: LoginRequest):BaseResponse<LoginResponse>{
        val accessToken: String = memberService.login(loginRequest)

        val loginResponse = LoginResponse(accessToken)

        return BaseResponse(ResultCode.SUCCESS, loginResponse, "로그인 성공하였습니다.")
    }

}
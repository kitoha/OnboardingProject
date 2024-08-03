package com.onboarding.practice.member.controller

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
    fun signUp(@RequestBody memberDto: MemberDto): String{
        return memberService.signUp(memberDto)
    }

}
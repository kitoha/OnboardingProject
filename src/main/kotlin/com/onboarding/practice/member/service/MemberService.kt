package com.onboarding.practice.member.service

import com.onboarding.practice.member.dto.LoginRequest
import com.onboarding.practice.member.dto.MemberDto
import com.onboarding.practice.member.entity.Member
import com.onboarding.practice.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    /***
     * 회원 가입 함수
     */
    fun signUp(memberDto: MemberDto):String{
        var member: Member? = memberRepository.findByEmail(memberDto.email)

        if(member!=null) {
            return "이미 가입한 회원입니다."
        }

        member = Member(
            null,
            memberDto.email,
            memberDto.password,
            memberDto.phoneNumber,
            memberDto.name,
        )

        memberRepository.save(member)

        return "회원 가입 완료하였습니다."
    }

    fun login(loginRequest: LoginRequest):String{
        return ""
    }

}
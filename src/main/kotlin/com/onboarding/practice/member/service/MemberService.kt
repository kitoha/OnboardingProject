package com.onboarding.practice.member.service

import com.onboarding.practice.authority.provider.JwtTokenProvider
import com.onboarding.practice.common.exception.DuplicateException
import com.onboarding.practice.member.dto.LoginRequest
import com.onboarding.practice.member.dto.MemberDto
import com.onboarding.practice.member.entity.Member
import com.onboarding.practice.member.repository.MemberRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder
) {

    /***
     * 회원 가입 함수
     */
    fun signUp(memberDto: MemberDto):String{

        memberDto.validate()

        var member: Member? = memberRepository.findByEmail(memberDto.email)

        if(member!=null) {
            throw DuplicateException("이미 가입한 회원입니다.")
        }

        val encryptedPassword : String = passwordEncoder.encode(memberDto.password)
        member = Member(
            null,
            memberDto.email,
            encryptedPassword,
            memberDto.phoneNumber,
            memberDto.name,
        )

        memberRepository.save(member)

        return "회원 가입 완료하였습니다."
    }

    fun login(loginRequest: LoginRequest):String{
        val usernamePasswordAuthenticationToken =  UsernamePasswordAuthenticationToken(loginRequest.email,loginRequest.password)
        val authentication:Authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        return jwtTokenProvider.createToken(authentication)
    }

}
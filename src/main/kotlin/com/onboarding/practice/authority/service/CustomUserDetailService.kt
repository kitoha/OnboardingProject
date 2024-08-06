package com.onboarding.practice.authority.service

import com.onboarding.practice.member.entity.Member
import com.onboarding.practice.member.repository.MemberRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomUserDetailService(
    private val memberRepository: MemberRepository
):UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val member: Member? = memberRepository.findByEmail(email)

        if(member!=null){
            return createUserDetails(member)
        }

        throw UsernameNotFoundException("해당 유저는 업습니다.")

    }

    fun createUserDetails(member: Member):UserDetails{
        val authority: GrantedAuthority = SimpleGrantedAuthority("ROLE_MEMBER")
        return User(member.email,member.password, Collections.singleton(authority))
    }
}
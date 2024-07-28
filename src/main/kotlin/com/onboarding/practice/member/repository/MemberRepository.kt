package com.onboarding.practice.member.repository

import com.onboarding.practice.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member,Long> {
    fun findByEmail(email:String): Member?
}
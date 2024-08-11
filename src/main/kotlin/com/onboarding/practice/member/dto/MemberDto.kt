package com.onboarding.practice.member.dto

import com.onboarding.practice.member.utils.MemberUtils


data class MemberDto(
    val email: String,
    val password: String,
    val phoneNumber: String,
    val name: String
){
    fun validate() {
        if (!MemberUtils.isValidEmail(email)) {
            throw IllegalArgumentException("이메일 형식이 잘못되었습니다.")
        }

        if (!MemberUtils.isValidPhoneNumber(phoneNumber)) {
            throw IllegalArgumentException("휴대폰 번호 형식이 잘못되었습니다.")
        }

        if (!MemberUtils.isValidAuthor(name)) {
            throw IllegalArgumentException("이름 형식이 잘못되었습니다.")
        }

        if (!MemberUtils.isValidPassword(password)) {
            throw IllegalArgumentException("비밀번호 형식이 잘못되었습니다.")
        }
    }
}
package com.onboarding.practice.member.utils

object MemberUtils {
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return email.matches(emailRegex)
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phoneNumberRegex = "^\\d{3}-\\d{3,4}-\\d{4}$".toRegex()
        return phoneNumber.matches(phoneNumberRegex)
    }

    fun isValidAuthor(author: String): Boolean {
        val authorRegex = "^[A-Za-z0-9가-힣]+$".toRegex()
        return author.matches(authorRegex)
    }

    fun isValidPassword(password: String): Boolean {
        val lengthCondition = password.length >= 5
        val upperCaseCondition = password.any { it.isUpperCase() }
        val lowerCaseCondition = password.any { it.isLowerCase() }
        val digitCondition = password.any { it.isDigit() }
        val specialCharCondition = password.count { it in "!@#$%^&*()-_=+[]{};:'\",.<>?/|`~" } >= 2

        return lengthCondition && upperCaseCondition && lowerCaseCondition && digitCondition && specialCharCondition
    }
}
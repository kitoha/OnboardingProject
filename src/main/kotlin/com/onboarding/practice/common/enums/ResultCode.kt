package com.onboarding.practice.common.enums

enum class ResultCode(val statusCode: String) {
    SUCCESS("200"),
    NOT_CONTENT("204"),
    UNAUTHORIZED("401"),
    FORBIDDEN("403"),
    DUPLICATE("409"),
    ERROR("500")
}
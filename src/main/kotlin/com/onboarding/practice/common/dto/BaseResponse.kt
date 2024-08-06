package com.onboarding.practice.common.dto

import com.onboarding.practice.common.enums.ResultCode

data class BaseResponse<T>(
    val resultCode: ResultCode,
    val data: T?,
    val message: String?
)

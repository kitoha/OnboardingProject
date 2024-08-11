package com.onboarding.practice.common.exception

import com.onboarding.practice.common.enums.ResultCode

open class CommonException(
    message: String,
    val resultCode: ResultCode
) : RuntimeException(message)
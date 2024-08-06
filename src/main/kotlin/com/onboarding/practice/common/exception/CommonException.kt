package com.onboarding.practice.common.exception

import com.onboarding.practice.common.enums.ResultCode

class CommonException(
    message: String,
    val resultCode: ResultCode
) : RuntimeException(message)
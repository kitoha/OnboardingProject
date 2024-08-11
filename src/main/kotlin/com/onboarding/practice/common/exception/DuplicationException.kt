package com.onboarding.practice.common.exception

import com.onboarding.practice.common.enums.ResultCode

class DuplicateException(message: String) : CommonException(message, ResultCode.DUPLICATE)
package com.onboarding.practice.common.exception

import com.onboarding.practice.common.enums.ResultCode

class ForbiddenException(messae: String): CommonException(messae,ResultCode.FORBIDDEN)
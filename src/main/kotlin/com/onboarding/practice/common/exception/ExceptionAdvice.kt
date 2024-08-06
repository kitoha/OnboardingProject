package com.onboarding.practice.common.exception

import com.onboarding.practice.common.dto.BaseResponse
import com.onboarding.practice.common.enums.ResultCode
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception): BaseResponse<*>{
        return BaseResponse(ResultCode.ERROR,null,e.message)
    }
}
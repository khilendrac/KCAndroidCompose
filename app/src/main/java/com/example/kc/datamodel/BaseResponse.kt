package com.example.kc.datamodel

import com.example.kc.common.Constants.EMPTY_STRING

open class BaseResponse {
    var isSuccess: Boolean? = false
    var message: String? = EMPTY_STRING
}
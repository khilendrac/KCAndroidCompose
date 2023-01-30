package com.example.kc.datamodel

import com.example.kc.common.Constants.EMPTY_STRING

class StateData<T : BaseResponse> {
    var loadingMessage: String? = EMPTY_STRING
    var successData: T? = null
    var errorData: ErrorResponse? = null
}
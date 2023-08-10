package com.example.kc.datamodel

import com.example.kc.common.Constants


data class DeviceResponse(
    val deviceData: List<Device>?
) : BaseResponse()

data class Device(
    val deviceName: String? = Constants.EMPTY_STRING,
    val serialNumber: String? = Constants.EMPTY_STRING
)
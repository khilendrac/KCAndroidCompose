package com.example.kc.viewmodels

import com.example.kc.datamodel.BaseResponse
import com.example.kc.datamodel.StateData

sealed class StateManager<out T : BaseResponse> {
    data class LOADING<T : BaseResponse>(val stateData: StateData<T>) : StateManager<T>()
    data class SUCCESS<T : BaseResponse>(val stateData: StateData<T>) : StateManager<T>()
    data class ERROR<T : BaseResponse>(val stateData: StateData<T>) : StateManager<T>()

    override fun toString(): String {
        return when (this) {
            is LOADING -> "Loading ${stateData.loadingMessage}"
            is SUCCESS -> "Success [data=${stateData.successData}]"
            is ERROR -> "Error [message = ${stateData.errorData}]"
        }
    }
}
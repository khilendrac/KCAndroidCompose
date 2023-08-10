package com.example.kc.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kc.R
import com.example.kc.common.getStringResource
import com.example.kc.datamodel.BaseResponse
import com.example.kc.datamodel.ErrorResponse
import com.example.kc.datamodel.StateData

open class BaseViewModel<T : BaseResponse> : ViewModel() {

    val state:MutableState<StateManager<T>> by lazy {
        mutableStateOf(StateManager.LOADING(StateData<T>().apply {
            loadingMessage = getStringResource(R.string.loading_please_wait)
        }))
    }

    fun onSuccess(data: T) {
        state.value = StateManager.SUCCESS(StateData<T>().apply { successData = data })
    }

    fun onError(data: ErrorResponse) {
        state.value = StateManager.ERROR(StateData<T>().apply { errorData = data })
    }

    fun onLoading(message: String) {
        state.value =
            StateManager.LOADING(StateData<T>().apply { loadingMessage = message })
    }
}
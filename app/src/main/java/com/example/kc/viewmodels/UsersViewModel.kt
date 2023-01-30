package com.example.kc.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kc.models.Users
import com.example.kc.network.ApiInterface
import com.example.kc.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class UsersViewModel: ViewModel() {
    private val _users: MutableLiveData<Users?> = MutableLiveData()
    val users: LiveData<Users?> = _users

    var error by mutableStateOf<Exception?>(null)

    init {
        getUsersList()
    }

    private fun getUsersList() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        viewModelScope.launch {
            try {
                val result = apiInterface.getAllUsers()
                if(result.isSuccessful) {
                    _users.value = result.body()!!
                } else {
                    throw Exception(result.errorBody().toString())
                }
            } catch (exception: Exception) {
                error = exception
            }
        }
    }
}
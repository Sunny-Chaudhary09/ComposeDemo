package com.example.mydemo.ui.login

import com.example.mydemo.core.mvi.MviState

sealed class LoginError {
    data object EmptyFields : LoginError()
    data object InvalidCredentials : LoginError()
    data object ApiError : LoginError()
}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: LoginError? = null,
    val navigateToOtp: Boolean = false
) : MviState

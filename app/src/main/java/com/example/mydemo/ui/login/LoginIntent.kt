package com.example.mydemo.ui.login

import com.example.mydemo.core.mvi.MviIntent

sealed class LoginIntent : MviIntent {
    data class EmailChanged(val value: String) : LoginIntent()
    data class PasswordChanged(val value: String) : LoginIntent()
    data object Submit : LoginIntent()
    data object ClearError : LoginIntent()
    data object NavigationHandled : LoginIntent()
}

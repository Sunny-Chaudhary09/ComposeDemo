package com.example.mydemo.ui.splash

import com.example.mydemo.core.mvi.MviState

data class SplashState(
    val isLoading: Boolean = true,
    val navigateToLogin: Boolean = false
) : MviState

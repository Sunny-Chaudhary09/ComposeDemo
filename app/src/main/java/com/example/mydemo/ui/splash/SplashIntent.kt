package com.example.mydemo.ui.splash

import com.example.mydemo.core.mvi.MviIntent

sealed class SplashIntent : MviIntent {
    data object Init : SplashIntent()
    data object NavigateToLogin : SplashIntent()
}

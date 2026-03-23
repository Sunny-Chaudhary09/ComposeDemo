package com.example.mydemo.ui.splash

import com.example.mydemo.core.mvi.BaseMviViewModel
import kotlinx.coroutines.delay

class SplashViewModel(
    private val splashDurationMs: Long = 2000L
) : BaseMviViewModel<SplashIntent, SplashState>(SplashState()) {

    override suspend fun handleIntent(intent: SplashIntent, currentState: SplashState): SplashState? {
        return when (intent) {
            is SplashIntent.Init -> {
                delay(splashDurationMs)
                currentState.copy(isLoading = false, navigateToLogin = true)
            }
            is SplashIntent.NavigateToLogin -> currentState.copy(navigateToLogin = false)
        }
    }
}

package com.example.mydemo.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Base ViewModel for MVI architecture.
 * @param I Intent type
 * @param S State type
 * @param initialState Initial state for the screen
 */
abstract class BaseMviViewModel<I : MviIntent, S : MviState>(
    initialState: S
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    fun sendIntent(intent: I) {
        viewModelScope.launch {
            handleIntent(intent, _state.value).let { newState ->
                if (newState != null) {
                    _state.update { newState }
                }
            }
        }
    }

    protected fun currentState(): S = _state.value

    protected fun setState(reducer: (S) -> S) {
        _state.update(reducer)
    }

    /**
     * Handle an intent and return the new state, or null to keep current state.
     * Side effects (e.g. navigation) can be exposed via state or a separate channel.
     */
    protected abstract suspend fun handleIntent(intent: I, currentState: S): S?
}

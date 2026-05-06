package com.example.mydemo.core.mvi

/**
 * Marker interface for MVI Intents (user actions / events).
 */
interface MviIntent{
    data class OnPay(val amount: String) : MviIntent
    data object OnPay1 : MviIntent
}

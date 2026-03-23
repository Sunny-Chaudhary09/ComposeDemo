package com.example.mydemo.ui.login

import com.example.mydemo.core.mvi.BaseMviViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class LoginViewModel : BaseMviViewModel<LoginIntent, LoginState>(LoginState()) {
    private val localTestEmail = "sanjeev@yopmail.com"
    private val localTestPassword = "12345678"

    private enum class LoginApiResult {
        SUCCESS,
        INVALID_CREDENTIALS,
        NETWORK_OR_SERVER_ERROR
    }

    override suspend fun handleIntent(intent: LoginIntent, currentState: LoginState): LoginState? {
        return when (intent) {
            is LoginIntent.EmailChanged -> currentState.copy(
                email = intent.value,
                error = null
            )
            is LoginIntent.PasswordChanged -> currentState.copy(
                password = intent.value,
                error = null
            )
            is LoginIntent.Submit -> {
                val email = currentState.email.trim()
                val password = currentState.password
                when {
                    email.isBlank() || password.isBlank() -> currentState.copy(
                        error = LoginError.EmptyFields
                    )
                    else -> {
                        setState {
                            it.copy(
                                isLoading = true,
                                error = null
                            )
                        }
                        val loginResult = performTestLoginApi(email = email, password = password)
                        currentState().copy(
                            isLoading = false,
                            error = when (loginResult) {
                                LoginApiResult.SUCCESS -> null
                                LoginApiResult.INVALID_CREDENTIALS -> LoginError.InvalidCredentials
                                LoginApiResult.NETWORK_OR_SERVER_ERROR -> LoginError.ApiError
                            },
                            navigateToOtp = loginResult == LoginApiResult.SUCCESS
                        )
                    }
                }
            }
            is LoginIntent.NavigationHandled -> currentState.copy(
                navigateToOtp = false
            )
            is LoginIntent.ClearError -> currentState.copy(error = null)
        }
    }

    private suspend fun performTestLoginApi(email: String, password: String): LoginApiResult {
        if (email.equals(localTestEmail, ignoreCase = true) && password == localTestPassword) {
            return LoginApiResult.SUCCESS
        }
        return withContext(Dispatchers.IO) {
            var connection: HttpURLConnection? = null
            try {
                val payload = """{"email":"$email","password":"$password"}"""
                val url = URL("https://reqres.in/api/login")
                connection = (url.openConnection() as HttpURLConnection).apply {
                    requestMethod = "POST"
                    doOutput = true
                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("x-api-key", "reqres-free-v1")
                    connectTimeout = 15000
                    readTimeout = 15000
                }
                connection.outputStream.use { output ->
                    output.write(payload.toByteArray())
                }
                when (connection.responseCode) {
                    in 200..299 -> LoginApiResult.SUCCESS
                    400, 401 -> LoginApiResult.INVALID_CREDENTIALS
                    else -> LoginApiResult.NETWORK_OR_SERVER_ERROR
                }
            } catch (_: Exception) {
                LoginApiResult.NETWORK_OR_SERVER_ERROR
            } finally {
                connection?.disconnect()
            }
        }
    }
}

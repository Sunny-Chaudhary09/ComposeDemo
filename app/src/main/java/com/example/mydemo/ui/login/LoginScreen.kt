package com.example.mydemo.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mydemo.R

@Preview(showSystemUi = true)
@Composable
fun LoginScreen(
    viewModel:  LoginViewModel = viewModel(),
    onNavigateToOtpDemo: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state.navigateToOtp) {
        if (state.navigateToOtp) {
            onNavigateToOtpDemo()
            viewModel.sendIntent(LoginIntent.NavigationHandled)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_primary))
            .padding(
                horizontal = dimensionResource(R.dimen.spacing_lg),
                vertical = dimensionResource(R.dimen.spacing_xl)
            ),
        verticalArrangement = Arrangement.Center
    )
    {
        Text(
            text = stringResource(R.string.login_welcome),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(R.dimen.text_size_headline).value.sp
            ),
            color = colorResource(R.color.text_primary)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_sm)))
        Text(
            text = stringResource(R.string.login_subtitle),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = dimensionResource(R.dimen.text_size_body_medium).value.sp
            ),
            color = colorResource(R.color.text_secondary)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xxl)))

        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.sendIntent(LoginIntent.EmailChanged(it)) },
            label = { Text(stringResource(R.string.login_email_hint)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.primary),
                unfocusedBorderColor = colorResource(R.color.text_hint),
                focusedLabelColor = colorResource(R.color.primary),
                unfocusedLabelColor = colorResource(R.color.text_secondary),
                cursorColor = colorResource(R.color.primary),
                focusedTextColor = colorResource(R.color.text_primary),
                unfocusedTextColor = colorResource(R.color.text_primary)
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_md)))

        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.sendIntent(LoginIntent.PasswordChanged(it)) },
            label = { Text(stringResource(R.string.login_password_hint)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.primary),
                unfocusedBorderColor = colorResource(R.color.text_hint),
                focusedLabelColor = colorResource(R.color.primary),
                unfocusedLabelColor = colorResource(R.color.text_secondary),
                cursorColor = colorResource(R.color.primary),
                focusedTextColor = colorResource(R.color.text_primary),
                unfocusedTextColor = colorResource(R.color.text_primary)
            )
        )

        state.error?.let { error ->
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_sm)))
            Text(
                text = when (error) {
                    is LoginError.EmptyFields -> stringResource(R.string.login_error_empty_fields)
                    is LoginError.InvalidCredentials -> stringResource(R.string.login_error_invalid_credentials)
                    is LoginError.ApiError -> stringResource(R.string.login_error_api)
                },
                color = colorResource(R.color.error),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_sm)))

        TextButton(
            onClick = { /* Forgot password */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = stringResource(R.string.login_forgot_password),
                color = colorResource(R.color.primary)
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_lg)))

        Button(
            onClick = { viewModel.sendIntent(LoginIntent.Submit) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = !state.isLoading,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary))
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.height(24.dp),
                    color = colorResource(R.color.text_on_primary),
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = stringResource(R.string.login_button),
                    fontSize = dimensionResource(R.dimen.text_size_body_large).value.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_md)))

        TextButton(
            onClick = {
                viewModel.sendIntent(LoginIntent.EmailChanged("sanjeev@yopmail.com"))
                viewModel.sendIntent(LoginIntent.PasswordChanged("12345678"))
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.login_use_test_credentials),
                color = colorResource(R.color.primary),
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xl)))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.login_no_account),
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.text_secondary)
            )
            TextButton(onClick = { /* Sign up */ }) {
                Text(
                    text = stringResource(R.string.login_sign_up),
                    color = colorResource(R.color.primary),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

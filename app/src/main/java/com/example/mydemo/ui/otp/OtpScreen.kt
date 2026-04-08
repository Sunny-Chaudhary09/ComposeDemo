package com.example.mydemo.ui.otp

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.example.mydemo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(
    onBackClick: () -> Unit = {},
    onNavigateToWithdraw: () -> Unit = {},
) {
    var otp by remember { mutableStateOf("") }
    var isVerified by remember { mutableStateOf(false) }
    var showCode by remember { mutableStateOf(false) }
    val isOtpValid = otp.length == 6

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.otp_toolbar_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.otp_back)
                        )
                    }
                }
            )
        },
        containerColor = colorResource(R.color.background_primary)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.background_primary))
                .padding(innerPadding)
                .padding(
                    horizontal = dimensionResource(R.dimen.spacing_lg),
                    vertical = dimensionResource(R.dimen.spacing_xl)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.otp_title),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(R.dimen.text_size_headline).value.sp
                ),
                color = colorResource(R.color.text_primary)
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_sm)))
            Text(
                text = stringResource(R.string.otp_subtitle),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = dimensionResource(R.dimen.text_size_body_medium).value.sp
                ),
                color = colorResource(R.color.text_secondary)
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_lg)))

            OutlinedTextField(
                value = otp,
                onValueChange = { input ->
                    otp = input.filter(Char::isDigit).take(6)
                    isVerified = false
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text(stringResource(R.string.otp_input_label)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                visualTransformation = if (showCode) VisualTransformation.None else PasswordVisualTransformation(),
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

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_sm)))
            TextButton(onClick = { showCode = !showCode }) {
                Text(
                    text = if (showCode) {
                        stringResource(R.string.otp_hide_code)
                    } else {
                        stringResource(R.string.otp_show_code)
                    },
                    color = colorResource(R.color.primary)
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_md)))

            Button(
                onClick = { isVerified = isOtpValid },
                enabled = isOtpValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary))
            ) {
                Text(text = stringResource(R.string.otp_verify_button))
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_sm)))

            TextButton(
                onClick = {
                    otp = ""
                    isVerified = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.otp_resend), color = colorResource(R.color.primary))
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_md)))

            val statusText = when {
                isVerified -> stringResource(R.string.otp_status_verified)
                otp.isNotBlank() -> stringResource(R.string.otp_status_pending)
                else -> stringResource(R.string.otp_status_idle)
            }
            Text(
                text = statusText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = if (isVerified) colorResource(R.color.secondary) else colorResource(R.color.text_secondary)
            )

            if (isVerified) {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_lg)))
                Button(
                    onClick = onNavigateToWithdraw,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary))
                ) {
                    Text(text = stringResource(R.string.otp_continue_withdraw))
                }
            }
        }
    }
}

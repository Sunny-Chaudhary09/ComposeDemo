package com.example.mydemo.ui.withdraw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydemo.R
import com.example.mydemo.ui.theme.MyDemoTheme

private val FieldShape = RoundedCornerShape(24.dp)
private val ButtonShape = RoundedCornerShape(28.dp)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithdrawScreen(
    onBackClick: () -> Unit = {},
) {
    val cardHint = stringResource(R.string.withdraw_card_hint)
    val phoneHint = stringResource(R.string.withdraw_phone_hint)
    val amountHint = stringResource(R.string.withdraw_amount_hint)
    var cardDisplay by remember(cardHint) { mutableStateOf(cardHint) }
    var phone by remember(phoneHint) { mutableStateOf(phoneHint) }
    var amount by remember(amountHint) { mutableStateOf(amountHint) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.withdraw_title),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.text_primary),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.withdraw_back),
                        )
                    }
                },
            )
        },
        containerColor = colorResource(R.color.background_primary),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = dimensionResource(R.dimen.spacing_lg)),
        ) {
            WithdrawHeroIllustration(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.spacing_md), bottom = dimensionResource(R.dimen.spacing_lg)),
            )

            OutlinedTextField(
                value = cardDisplay,
                onValueChange = { cardDisplay = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = FieldShape,
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = dimensionResource(R.dimen.text_size_body_medium).value.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.text_hint),
                    unfocusedBorderColor = colorResource(R.color.text_hint),
                    focusedContainerColor = colorResource(R.color.background_primary),
                    unfocusedContainerColor = colorResource(R.color.background_primary),
                    cursorColor = colorResource(R.color.withdraw_accent),
                    focusedTextColor = colorResource(R.color.text_primary),
                    unfocusedTextColor = colorResource(R.color.text_primary),
                ),
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_sm)))
            Text(
                text = stringResource(R.string.withdraw_available_balance, "10,000$"),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = dimensionResource(R.dimen.text_size_body_small).value.sp,
                    fontWeight = FontWeight.Medium,
                ),
                color = colorResource(R.color.withdraw_accent),
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_md)))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = FieldShape,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = dimensionResource(R.dimen.text_size_body_medium).value.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.text_hint),
                    unfocusedBorderColor = colorResource(R.color.text_hint),
                    focusedContainerColor = colorResource(R.color.background_primary),
                    unfocusedContainerColor = colorResource(R.color.background_primary),
                    cursorColor = colorResource(R.color.withdraw_accent),
                    focusedTextColor = colorResource(R.color.text_primary),
                    unfocusedTextColor = colorResource(R.color.text_primary),
                ),
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_md)))

            Text(
                text = stringResource(R.string.withdraw_amount_label),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = dimensionResource(R.dimen.text_size_caption).value.sp,
                ),
                color = colorResource(R.color.text_secondary),
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xs)))

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = FieldShape,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = dimensionResource(R.dimen.text_size_body_medium).value.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.text_hint),
                    unfocusedBorderColor = colorResource(R.color.text_hint),
                    focusedContainerColor = colorResource(R.color.background_primary),
                    unfocusedContainerColor = colorResource(R.color.background_primary),
                    cursorColor = colorResource(R.color.withdraw_accent),
                    focusedTextColor = colorResource(R.color.text_primary),
                    unfocusedTextColor = colorResource(R.color.text_primary),
                ),
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xl)))

            Button(
                onClick = { /* Hook verify / API */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = ButtonShape,
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.withdraw_accent)),
            ) {
                Text(
                    text = stringResource(R.string.withdraw_verify),
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(R.dimen.text_size_body_large).value.sp,
                    color = colorResource(R.color.text_on_primary),
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xl)))
        }
    }
}

@Composable
private fun WithdrawHeroIllustration(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .aspectRatio(1.35f)
                .clip(RoundedCornerShape(percent = 50))
                .background(colorResource(R.color.withdraw_hero_surface)),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .size(width = 28.dp, height = 8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(colorResource(R.color.withdraw_coin)),
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp, 64.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(colorResource(R.color.withdraw_phone_blue)),
                    )
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .clip(CircleShape)
                            .background(colorResource(R.color.withdraw_coin)),
                    )
                }
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = colorResource(R.color.text_hint),
                    modifier = Modifier.size(20.dp),
                )
                Box(
                    modifier = Modifier
                        .width(72.dp)
                        .height(52.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(colorResource(R.color.withdraw_wallet_blue)),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WithdrawScreenPreview() {
    MyDemoTheme {
        WithdrawScreen()
    }
}

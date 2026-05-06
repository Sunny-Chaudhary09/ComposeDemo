package com.example.mydemo.ui.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PrimaryPurple = Color(0xFF5B4FCF)
private val SuccessGreen = Color(0xFF4CAF50)
private val ErrorRed = Color(0xFFF44336)
private val CompanyPurple = Color(0xFF7B6FE0)
private val BackgroundGray = Color(0xFFF5F5F5)
private val ChipUnselectedBg = Color(0xFFEEEEEE)
private val ChipUnselectedText = Color(0xFF757575)
private val DividerColor = Color(0xFFE0E0E0)

enum class PaymentCategory { WATER, MOBILE, INTERNET }

data class PaymentItem(
    val month: String,
    val date: String,
    val status: String,
    val isSuccessful: Boolean,
    val company: String,
    val amount: String
)

private val samplePayments = listOf(
    PaymentItem("October", "30/10/2019", "Unsuccessfully", false, "Capi Telecom", "\$50"),
    PaymentItem("September", "30/10/2019", "Successfully", true, "Capi Telecom", "\$50"),
    PaymentItem("August", "30/10/2019", "Successfully", true, "Capi Telecom", "\$50"),
    PaymentItem("July", "30/10/2019", "Successfully", true, "Capi Telecom", "\$50"),
    PaymentItem("June", "30/10/2019", "Successfully", true, "Capi Telecom", "\$50"),
)

//@Preview(showSystemUi = true)
@Composable
fun PaymentScreen(onBackClick: () -> Unit = {}) {
    var selectedCategory by remember { mutableStateOf(PaymentCategory.INTERNET) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        PaymentTopBar(onBackClick = onBackClick)
        CategoryFilterRow(
            selected = selectedCategory,
            onSelect = { selectedCategory = it }
        )
        HorizontalDivider(color = DividerColor, thickness = 1.dp)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(samplePayments) { item ->
                PaymentHistoryCard(item = item)
            }
        }
    }
}

@Composable
private fun PaymentTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }
        Text(
            text = "Payment history",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}

@Composable
private fun CategoryFilterRow(
    selected: PaymentCategory,
    onSelect: (PaymentCategory) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CategoryChip(
            label = "Water",
            isSelected = selected == PaymentCategory.WATER,
            onClick = { onSelect(PaymentCategory.WATER) }
        )
        CategoryChip(
            label = "Mobile",
            isSelected = selected == PaymentCategory.MOBILE,
            onClick = { onSelect(PaymentCategory.MOBILE) }
        )
        CategoryChip(
            label = "Internet",
            isSelected = selected == PaymentCategory.INTERNET,
            onClick = { onSelect(PaymentCategory.INTERNET) }
        )
    }
}

@Composable
private fun CategoryChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) PrimaryPurple else ChipUnselectedBg)
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            color = if (isSelected) Color.White else ChipUnselectedText,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

@Composable
private fun PaymentHistoryCard(item: PaymentItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.month,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = item.date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Status  ",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = item.status,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (item.isSuccessful) SuccessGreen else ErrorRed
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Company  ",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = item.company,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = CompanyPurple
                        )
                    }
                }
                Text(
                    text = "Amount  ",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Text(
                    text = item.amount,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen()
}

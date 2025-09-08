package com.quan.homwork1.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    mainText: String,
    subText: String = "",
    mainTextSize: TextUnit = 16.sp,
    subTextSize: TextUnit = 14.sp,
    mainTextColor: Color = Color(0xFF5B6B86),
    subTextColor: Color = Color(0xFF8292AA),
    mainTextWeight: FontWeight = FontWeight.W400,
    subTextWeight: FontWeight = FontWeight.W400
) {
    Row(modifier = modifier) {
        Text(
            text = mainText,
            fontSize = mainTextSize,
            color = mainTextColor,
            fontWeight = mainTextWeight,
        )
        if (subText.isNotEmpty()) {
            Text(
                text = subText,
                fontSize = subTextSize,
                color = subTextColor,
                fontWeight = subTextWeight,
            )
        }
    }
}
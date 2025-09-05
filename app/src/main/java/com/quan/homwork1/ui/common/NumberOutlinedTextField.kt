package com.quan.homwork1.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberInputField(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    minValue: Int = 0,
    maxValue: Int? = null,
    readOnly: Boolean = true,
    singleLine: Boolean = true,
    fontSize: TextUnit = 14.sp,
    lineHeight: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.W400,
    activeTextColor: Color = Color(0xFF5B6B86),
    inactiveTextColor: Color = Color(0xFFBABDC5),
    iconColor: Color = Color(0xFF5B6B86),
    focusedIndicatorColor: Color = Color(0xFFC8CED9),
    unfocusedIndicatorColor: Color = Color(0xFFC8CED9),
    containerColor: Color = Color.Transparent,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(10.dp),
    width: androidx.compose.ui.unit.Dp = 160.dp,
    height: androidx.compose.ui.unit.Dp = 50.dp,
    leadingIconSize: androidx.compose.ui.unit.Dp = 48.dp,
    decreaseContentDescription: String = "Decrease",
    increaseContentDescription: String = "Increase"
) {
    OutlinedTextField(
        value = value.toString(),
        onValueChange = { },
        readOnly = readOnly,
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
        ),
        leadingIcon = {
            IconButton(
                onClick = {
                    if (value > minValue) {
                        onValueChange(value - 1)
                    }
                },
                modifier = Modifier.size(leadingIconSize),
                enabled = value > minValue
            ) {
                Icon(
                    Icons.Default.Remove,
                    contentDescription = decreaseContentDescription,
                    tint = if (value > minValue) iconColor else iconColor.copy(alpha = 0.5f)
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    if (maxValue == null || value < maxValue) {
                        onValueChange(value + 1)
                    }
                },
                enabled = maxValue == null || value < maxValue
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = increaseContentDescription,
                    tint = if (maxValue == null || value < maxValue) iconColor else iconColor.copy(alpha = 0.5f)
                )
            }
        },
        textStyle = LocalTextStyle.current.copy(
            fontSize = fontSize,
            lineHeight = lineHeight,
            fontWeight = fontWeight,
            color = if (value == 0) inactiveTextColor else activeTextColor,
            textAlign = TextAlign.Center
        ),
        shape = shape,
        modifier = modifier.size(width, height)
    )
}
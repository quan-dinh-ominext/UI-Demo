package com.quan.homwork1.ui.components.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quan.homwork1.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DateNavigationBar(
    currentDate: Date,
    onDateChanged: (Date) -> Unit,
    modifier: Modifier = Modifier,
    dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd (E)", Locale.JAPANESE),
    showTodayIndicator: Boolean = true,
    enableFutureNavigation: Boolean = true,
    todayText: String = "本日"
) {
    val calendar = Calendar.getInstance()
    val today = Calendar.getInstance().time

    val isToday = SimpleDateFormat("yyyy/MM/dd", Locale.JAPANESE).format(currentDate) ==
            SimpleDateFormat("yyyy/MM/dd", Locale.JAPANESE).format(today)

    val todayIndicator = if (showTodayIndicator && isToday) " • $todayText" else ""

    calendar.time = today
    calendar.set(Calendar.HOUR_OF_DAY, 23)
    calendar.set(Calendar.MINUTE, 59)
    calendar.set(Calendar.SECOND, 59)
    val endOfToday = calendar.time

    val isFutureDate = currentDate.after(endOfToday)
    val canNavigateForward = enableFutureNavigation || !isFutureDate

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                calendar.time = currentDate
                calendar.add(Calendar.DAY_OF_MONTH, -1)
                onDateChanged(calendar.time)
            }
        ) {
            Icon(
                Icons.Default.ArrowBackIosNew,
                contentDescription = "Previous Day",
                tint = Color.Gray
            )
        }

        Text(
            "${dateFormat.format(currentDate)}$todayIndicator",
            fontSize = 14.sp,
            lineHeight = 16.sp,
            color = TextSecondary,
            fontWeight = FontWeight.W700,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        IconButton(
            onClick = {
                if (canNavigateForward) {
                    calendar.time = currentDate
                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                    onDateChanged(calendar.time)
                }
            },
            enabled = canNavigateForward
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Next Day",
                tint = if (canNavigateForward) Color.Gray else Color.LightGray
            )
        }
    }
}


package com.quan.homwork1.ui.components.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class TimePeriod(val displayName: String, val icon: String) {
    MORNING("午前", "🌅"),
    AFTERNOON("午後", "🌆")
}

@Composable
fun SimpleSwitchButton(
    selectedPeriod: TimePeriod,
    onPeriodChange: (TimePeriod) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                Color(0xFFF5F5F5),
                RoundedCornerShape(20.dp)
            )
            .padding(2.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TimePeriod.entries.forEach { period ->
            val isSelected = period == selectedPeriod

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        if (isSelected) Color.White else Color.Transparent
                    )
                    .border(
                        width = if (isSelected) 0.5.dp else 0.dp,
                        color = if (isSelected) Color(0xFFE0E0E0) else Color.Transparent,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .clickable { onPeriodChange(period) }
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = period.icon,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = period.displayName,
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                        color = if (isSelected) Color(0xFF007AFF) else Color(0xFF666666)
                    )
                }
            }
        }
    }
}

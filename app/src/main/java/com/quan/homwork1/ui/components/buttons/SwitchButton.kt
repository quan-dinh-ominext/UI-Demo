package com.quan.homwork1.ui.components.buttons

import android.app.TimePickerDialog
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quan.homwork1.R
import com.quan.homwork1.ui.theme.BackgroundColorSwitch
import com.quan.homwork1.ui.theme.BorderColor
import com.quan.homwork1.ui.theme.Dimens
import com.quan.homwork1.ui.theme.TextPrimary
import java.util.Calendar

//enum class TimePeriod(var displayName: String) {
//    MORNING("午前",),
//    AFTERNOON("午後",)
//}

@Composable
fun SimpleSwitchButton(
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val options = listOf("午前", "午后")

    Row(
        modifier = modifier
            .background(
                color = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(25.dp)
            )
            .padding(4.dp)
    ) {
        options.forEach { option ->
            val isSelected = option == selectedOption

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        color = if (isSelected) Color(0xFF007AFF) else Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable { onOptionSelected(option) }
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Add icon if needed
                    if (option == "午前") {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sun),
                            contentDescription = "Sun Icon",
                            tint = if (isSelected) Color.White else Color.Gray,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_moon),
                            contentDescription = "Moon Icon",
                            tint = if (isSelected) Color.White else Color.Gray,
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))
                    }

                    Text(
                        text = option,
                        color = if (isSelected) Color.White else Color(0xFF666666),
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                    )
                }
            }
        }
    }
}


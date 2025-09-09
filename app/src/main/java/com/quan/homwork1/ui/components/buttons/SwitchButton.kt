package com.quan.homwork1.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quan.homwork1.R
import com.quan.homwork1.ui.theme.ColorUnselected
import com.quan.homwork1.ui.theme.TextBlue

@Composable
fun CustomSwitchButton(
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
                        color = if (isSelected) Color.White else Color.Transparent
                    )
                    .clickable { onOptionSelected(option) }
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (option == "午前") {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sun),
                            contentDescription = "Sun Icon",
                            tint = if (isSelected) TextBlue else ColorUnselected,
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_moon),
                            contentDescription = "Sun Icon",
                            tint = if (isSelected) TextBlue else ColorUnselected,
                        )
                    }

                    Text(
                        text = option,
                        color = if (isSelected) TextBlue else ColorUnselected,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500
                    )
                }
            }
        }
    }
}

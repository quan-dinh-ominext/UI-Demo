package com.quan.homwork1.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quan.homwork1.ui.components.common.DateNavigationBar
import java.util.Date

//@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SmokeScreen(
    modifier: Modifier = Modifier
) {
    var count by remember { mutableIntStateOf(0) }
    var selectedDate by remember { mutableStateOf(Date()) }

    Column(
        modifier = modifier
            .fillMaxSize().padding(horizontal = 16.dp)

    ) {

        DateNavigationBar(
            currentDate = selectedDate,
            onDateChanged = { newDate ->
                selectedDate = newDate
            }
        )

        Row {
            Text(
                text = "本数",
                fontSize = 16.sp,
                color = Color(0xFF5B6B86),
                lineHeight = 16.sp,
                fontWeight = FontWeight.W400,
            )
            Text(
                text = " (本)",
                fontSize = 14.sp,
                color = Color(0xFF8292AA),
                lineHeight = 16.sp,
                fontWeight = FontWeight.W400,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = count.toString(),
            onValueChange = { },
            readOnly = true,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFFC8CED9),
                unfocusedIndicatorColor = Color(0xFFC8CED9),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
            leadingIcon = {
                IconButton(
                    onClick = { if (count > 0) count-- },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        Icons.Default.Remove,
                        contentDescription = "Decrease",
                        tint = Color(0xFF5B6B86)
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = { count++ }
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Increase",
                        tint = Color(0xFF5B6B86)
                    )
                }
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.W400,
                color = if (count == 0) Color(0xFFBABDC5) else Color(0xFF5B6B86),
                textAlign = TextAlign.Center
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.size(160.dp, 50.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "合計を入力してください。",
            fontSize = 13.sp,
            lineHeight = 16.sp,
            color = Color(0xFF5B6B86),
        )

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            border = BorderStroke(1.dp, Color.Transparent),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4E8EFE)
            )

        ) {
            Text(
                text = "記録",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 16.sp
            )
        }
    }
}

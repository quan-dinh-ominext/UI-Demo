package com.quan.homwork1.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quan.homwork1.ui.common.SectionTitle
import com.quan.homwork1.ui.components.common.CustomOutlinedTextField
import com.quan.homwork1.ui.components.common.DateNavigationBar
import java.util.Date

import androidx.compose.material3.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SleepTrackingScreen() {
    var bedtime by remember { mutableStateOf("") }
    var wakeupTime by remember { mutableStateOf("") }
    var sleepQuality by remember { mutableStateOf("") }
    var showDropdown by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(Date()) }
    val listSleep = listOf("とても良い", "良い", "普通", "悪い", "とても悪い")

    val icon = if (showDropdown)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)

        ) {

            DateNavigationBar(
                currentDate = selectedDate,
                onDateChanged = { newDate ->
                    selectedDate = newDate
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TimeInputWithPicker(
                    time = bedtime,
                    title = "前日の就寝時間",
                    onTimeChange = { bedtime = it },
                    modifier = Modifier.weight(1f)
                )

                TimeInputWithPicker(
                    time = wakeupTime,
                    title = "起床時間",
                    onTimeChange = { wakeupTime = it },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle(mainText = "睡眠の質")

            ExposedDropdownMenuBox(
                expanded = showDropdown,
                onExpandedChange = { showDropdown = !showDropdown }
            ) {
                CustomOutlinedTextField(
                    value = sleepQuality,
                    onValueChange = { },
                    isEnable = false,
                    placeholder = "選択してください",
                    trailingIcon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                )

                ExposedDropdownMenu(
                    expanded = showDropdown,
                    onDismissRequest = { showDropdown = false },
                    containerColor = Color.White
                ) {
                    listSleep.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400,
                                lineHeight = 16.sp
                                ) },
                            onClick = {
                                sleepQuality = option
                                showDropdown = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
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

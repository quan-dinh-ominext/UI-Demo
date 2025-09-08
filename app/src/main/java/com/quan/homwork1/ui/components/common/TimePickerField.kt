package com.quan.homwork1.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimePickerField(
    time: String,
    onTimeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showTimePicker by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .clickable { showTimePicker = true }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Schedule,
                contentDescription = "時間選択",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (time.isEmpty()) "00:00" else time,
                fontSize = 16.sp,
                color = if (time.isEmpty()) Color.Gray.copy(alpha = 0.6f) else Color.Black
            )
        }
    }

    if (showTimePicker) {
        TimePickerDialog(
            onTimeSelected = { selectedTime ->
                onTimeSelected(selectedTime)
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false },
            initialTime = time.ifEmpty { "07:00" }
        )
    }
}

@Composable
fun TimePickerDialog(
    onTimeSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    initialTime: String
) {
    var selectedHour by remember {
        mutableIntStateOf(
            try {
                initialTime.split(":")[0].toInt()
            } catch (e: Exception) { 7 }
        )
    }
    var selectedMinute by remember {
        mutableIntStateOf(
            try {
                initialTime.split(":")[1].toInt()
            } catch (e: Exception) { 0 }
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("時間を選択") },
        text = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Hour picker
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("時", fontSize = 12.sp, color = Color.Gray)
                    NumberPicker(
                        value = selectedHour,
                        range = 0..23,
                        onValueChange = { selectedHour = it }
                    )
                }

                Text(":", fontSize = 20.sp)

                // Minute picker
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("分", fontSize = 12.sp, color = Color.Gray)
                    NumberPicker(
                        value = selectedMinute,
                        range = 0..59,
                        step = 5,
                        onValueChange = { selectedMinute = it }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                    onTimeSelected(formattedTime)
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("キャンセル")
            }
        }
    )
}

@Composable
fun NumberPicker(
    value: Int,
    range: IntRange,
    step: Int = 1,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
                val newValue = (value + step).coerceAtMost(range.last)
                onValueChange(newValue)
            }
        ) {
            Text("▲", fontSize = 12.sp)
        }

        Text(
            text = String.format("%02d", value),
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        IconButton(
            onClick = {
                val newValue = (value - step).coerceAtLeast(range.first)
                onValueChange(newValue)
            }
        ) {
            Text("▼", fontSize = 12.sp)
        }
    }
}

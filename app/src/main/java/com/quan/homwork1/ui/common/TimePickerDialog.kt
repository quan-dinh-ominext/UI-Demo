
package com.quan.homwork1.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.text.SimpleDateFormat
import java.util.*

/**
 * Common Time Picker Dialog Component using Material3 TimePicker
 *
 * @param onTimeSelected Callback when time is selected (hour, minute)
 * @param onDismiss Callback when dialog is dismissed
 * @param initialHour Initial hour (0-23)
 * @param initialMinute Initial minute (0-59)
 * @param is24Hour Whether to use 24-hour format
 * @param title Dialog title
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit,
    initialHour: Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
    initialMinute: Int = Calendar.getInstance().get(Calendar.MINUTE),
    is24Hour: Boolean = true,
    title: String = "時間を選択"
) {
    val timePickerState = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = is24Hour
    )

    var showingPicker by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(
                    if (configuration.screenWidthDp > 400) 400.dp
                    else (configuration.screenWidthDp * 0.9f).dp
                )
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surface
                )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Time Display and Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Selected Time Display
                    Text(
                        text = String.format(
                            if (is24Hour) "%02d:%02d" else "%d:%02d %s",
                            if (is24Hour) timePickerState.hour else {
                                if (timePickerState.hour == 0) 12
                                else if (timePickerState.hour > 12) timePickerState.hour - 12
                                else timePickerState.hour
                            },
                            timePickerState.minute,
                            if (!is24Hour) {
                                if (timePickerState.hour < 12) "AM" else "PM"
                            } else ""
                        ),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    // Toggle between Picker and Input
                    IconButton(
                        onClick = { showingPicker = !showingPicker }
                    ) {
                        Icon(
                            imageVector = if (showingPicker) Icons.Default.Keyboard else Icons.Default.AccessTime,
                            contentDescription = if (showingPicker) "Switch to text input" else "Switch to touch input",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Time Picker
                if (showingPicker) {
                    TimePicker(
                        state = timePickerState,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    TimeInput(
                        state = timePickerState,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(
                            "キャンセル",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            onTimeSelected(timePickerState.hour, timePickerState.minute)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            "OK",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

/**
 * Simple Time Picker Dialog with minimal UI
 */

@Composable
fun SimpleTimePickerDialog(
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit,
    initialTime: String = "07:00" // Format: "HH:MM"
) {
    val timeParts = initialTime.split(":")
    val initialHour = timeParts.getOrNull(0)?.toIntOrNull() ?: 7
    val initialMinute = timeParts.getOrNull(1)?.toIntOrNull() ?: 0

    TimePickerDialog(
        onTimeSelected = onTimeSelected,
        onDismiss = onDismiss,
        initialHour = initialHour,
        initialMinute = initialMinute,
        title = "時間選択"
    )
}

/**
 * Time Picker Dialog with custom styling
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePickerDialog(
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit,
    initialHour: Int = 7,
    initialMinute: Int = 0,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    primaryColor: Color = Color(0xFF4285F4),
    title: String = "Select Time"
) {
    val timePickerState = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = true
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            tonalElevation = 8.dp,
            color = backgroundColor,
            modifier = Modifier
                .width(360.dp)
                .background(
                    shape = RoundedCornerShape(20.dp),
                    color = backgroundColor
                )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Custom Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.Schedule,
                        contentDescription = null,
                        tint = primaryColor,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = primaryColor
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Time Display
                Text(
                    text = String.format("%02d:%02d", timePickerState.hour, timePickerState.minute),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Time Picker
                TimePicker(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = backgroundColor,
                        selectorColor = primaryColor
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = primaryColor
                        )
                    ) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = {
                            onTimeSelected(timePickerState.hour, timePickerState.minute)
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor
                        )
                    ) {
                        Text("Select", color = Color.White)
                    }
                }
            }
        }
    }
}

/**
 * Compact Time Picker Dialog for small screens
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompactTimePickerDialog(
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit,
    initialTime: String = "07:00"
) {
    val timeParts = initialTime.split(":")
    val initialHour = timeParts.getOrNull(0)?.toIntOrNull() ?: 7
    val initialMinute = timeParts.getOrNull(1)?.toIntOrNull() ?: 0

    val timePickerState = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = true
    )

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 4.dp,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "時間",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Use TimeInput for compact view
                TimeInput(
                    state = timePickerState,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    TextButton(
                        onClick = {
                            onTimeSelected(timePickerState.hour, timePickerState.minute)
                        }
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}

/**
 * Time Picker with range validation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeRangePickerDialog(
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit,
    initialHour: Int = 7,
    initialMinute: Int = 0,
    minHour: Int = 0,
    maxHour: Int = 23,
    minuteInterval: Int = 1 // 1, 5, 15, 30 minutes
) {
    val timePickerState = rememberTimePickerState(
        initialHour = initialHour.coerceIn(minHour, maxHour),
        initialMinute = initialMinute,
        is24Hour = true
    )

    var isValidTime by remember { mutableStateOf(true) }

    // Validate time range
    LaunchedEffect(timePickerState.hour) {
        isValidTime = timePickerState.hour in minHour..maxHour
    }

    TimePickerDialog(
        onTimeSelected = { hour, minute ->
            if (hour in minHour..maxHour) {
                val adjustedMinute = (minute / minuteInterval) * minuteInterval
                onTimeSelected(hour, adjustedMinute)
            }
        },
        onDismiss = onDismiss,
        initialHour = initialHour,
        initialMinute = initialMinute,
        title = "時間を選択 ($minHour:00 - $maxHour:00)"
    )
}

/**
 * Helper function to format time
 */
object TimePickerUtils {
    fun formatTime(hour: Int, minute: Int, is24Hour: Boolean = true): String {
        return if (is24Hour) {
            String.format("%02d:%02d", hour, minute)
        } else {
            val displayHour = when {
                hour == 0 -> 12
                hour > 12 -> hour - 12
                else -> hour
            }
            val amPm = if (hour < 12) "AM" else "PM"
            String.format("%d:%02d %s", displayHour, minute, amPm)
        }
    }

    fun parseTime(timeString: String): Pair<Int, Int> {
        val parts = timeString.split(":")
        val hour = parts.getOrNull(0)?.toIntOrNull() ?: 0
        val minute = parts.getOrNull(1)?.toIntOrNull() ?: 0
        return Pair(hour.coerceIn(0, 23), minute.coerceIn(0, 59))
    }

    fun getCurrentTime(): Pair<Int, Int> {
        val calendar = Calendar.getInstance()
        return Pair(
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE)
        )
    }
}
package com.quan.homwork1.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.util.Calendar

@Composable
fun BloodGlucoseScreen() {
    var bloodSugarValue by remember { mutableStateOf("95") }
    var selectedHour by remember { mutableStateOf(7) }
    var selectedMinute by remember { mutableStateOf(0) }
    var selectedPeriod by remember { mutableStateOf("午前") }
    var selectedTab by remember { mutableStateOf("血糖") }
    var showTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Date Navigation
            item {
                DateNavigationRow()
            }
           
            item{
                DialExample(
                    onConfirm = TODO(),
                    onDismiss = TODO()
                )
            }

            // Blood Sugar Input Section
            item {
                BloodSugarInputSection(
                    bloodSugarValue = bloodSugarValue,
                    onBloodSugarValueChange = { bloodSugarValue = it },
                    selectedHour = selectedHour,
                    selectedMinute = selectedMinute,
                    onTimeClick = { showTimePicker = true },
                    selectedPeriod = selectedPeriod,
                    onPeriodChange = { selectedPeriod = it }
                )
            }

            // Today's Values
            item {
                TodaysValuesSection()
            }

            // Measurement Time Info
            item {
                MeasurementTimeInfo()
            }

            // Reference Cards
            item {
                ReferenceCardsSection()
            }

            // Record Button
            item {
                Button(
                    onClick = { /* Handle record */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors( Color(0xFF4285F4)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "記録",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }

    // Time Picker Dialog
    if (showTimePicker) {
        TimePickerDialog(
            selectedHour = selectedHour,
            selectedMinute = selectedMinute,
            onTimeSelected = { hour, minute ->
                selectedHour = hour
                selectedMinute = minute
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    selectedHour: Int,
    selectedMinute: Int,
    onTimeSelected: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute,
        is24Hour = true
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "時間を選択",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        },
        text = {
            TimePicker(
                state = timePickerState,
                modifier = Modifier.padding(16.dp)
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onTimeSelected(timePickerState.hour, timePickerState.minute)
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
fun BloodSugarInputSection(
    bloodSugarValue: String,
    onBloodSugarValueChange: (String) -> Unit,
    selectedHour: Int,
    selectedMinute: Int,
    onTimeClick: () -> Unit,
    selectedPeriod: String,
    onPeriodChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
//        backgroundColor = Color.White,
//        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Blood sugar input
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "血糖 (mg/dL)",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = bloodSugarValue,
                        onValueChange = onBloodSugarValueChange,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "測定時間",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = String.format("%02d:%02d", selectedHour, selectedMinute),
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onTimeClick() },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = "Time"
                            )
                        },
                        singleLine = true,
                        readOnly = true,
                        enabled = false,
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            disabledTextColor = Color.Black,
//                            disabledBorderColor = Color.Gray,
//                            disabledLeadingIconColor = Color.Gray
//                        )
                    )
                }
            }

            // Period selection
            Column {
                Text(
                    text = "測定期間",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PeriodButton(
                        text = "🌅 午前",
                        selected = selectedPeriod == "午前",
                        onClick = { onPeriodChange("午前") }
                    )
                    PeriodButton(
                        text = "🌙 午後",
                        selected = selectedPeriod == "午後",
                        onClick = { onPeriodChange("午後") }
                    )
                }
            }
        }
    }
}

// Alternative Compact Time Picker Dialog
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompactTimePickerDialog(
    selectedHour: Int,
    selectedMinute: Int,
    onTimeSelected: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute,
        is24Hour = true
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "時間を選択",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                TimeInput(
                    state = timePickerState,
                    modifier = Modifier.padding(16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("キャンセル")
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

// Rest of the composables remain the same...
@Composable
fun DateNavigationRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Previous day */ }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous"
            )
        }

        Text(
            text = "2025/04/13 (日) • 本日",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        IconButton(onClick = { /* Next day */ }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next"
            )
        }
    }
}

@Composable
fun PeriodButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = if (selected) Color(0xFF4285F4) else Color(0xFFE0E0E0),
//            contentColor = if (selected) Color.White else Color.Gray
//        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.height(36.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}

// ... (rest of the composables remain the same as in the previous code)

@Composable
fun TodaysValuesSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
//        backgroundColor = Color.White,
//        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "本日の血糖値",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            BloodSugarEntry("午後", "17:00", "105 mg/dL")
//            Divider(modifier = Modifier.padding(vertical = 8.dp))
            BloodSugarEntry("午前", "07:00", "95 mg/dL")
        }
    }
}

@Composable
fun BloodSugarEntry(period: String, time: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = period,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = time,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = value,
                fontSize = 14.sp
            )
            IconButton(onClick = { /* Delete entry */ }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun MeasurementTimeInfo() {
    Card(
        modifier = Modifier.fillMaxWidth(),
//        backgroundColor = Color.White,
//        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = "Schedule",
                    tint = Color.Blue,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "測定時間について",
                    fontSize = 14.sp,
                    color = Color.Blue
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "血糖値（静脈血漿値）",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ReferenceCardsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ReferenceCard(
            title = "糖尿病型",
            color = Color(0xFF4285F4),
            leftValue = "126",
            leftUnit = "mg/dL以上",
            leftLabel = "空腹時",
            rightValue = "200",
            rightUnit = "mg/dL以上",
            rightLabel = "食後2時間"
        )

        ReferenceCard(
            title = "正常型",
            color = Color(0xFF4285F4),
            leftValue = "110",
            leftUnit = "mg/dL未満",
            leftLabel = "あーおあびー",
            rightValue = "140",
            rightUnit = "mg/dL未満",
            rightLabel = ""
        )
    }
}

@Composable
fun ReferenceCard(
    title: String,
    color: Color,
    leftValue: String,
    leftUnit: String,
    leftLabel: String,
    rightValue: String,
    rightUnit: String,
    rightLabel: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
//        backgroundColor = Color.White,
//        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Button(
                onClick = { },
//                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = leftLabel,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = leftValue,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = leftUnit,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = rightLabel,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = rightValue,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = rightUnit,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialExample(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column {
        TimePicker(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Dismiss picker")
        }
        Button(onClick = onConfirm) {
            Text("Confirm selection")
        }
    }
}
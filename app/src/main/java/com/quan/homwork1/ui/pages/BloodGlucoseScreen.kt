package com.quan.homwork1.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.quan.homwork1.R
import com.quan.homwork1.ui.common.SectionTitle
import com.quan.homwork1.ui.components.buttons.CustomSwitchButton
import com.quan.homwork1.ui.components.common.CustomOutlinedTextField
import com.quan.homwork1.ui.components.common.DateNavigationBar
import com.quan.homwork1.ui.theme.BorderColor
import com.quan.homwork1.ui.theme.ColorUnselected
import com.quan.homwork1.ui.theme.TextBlue
import java.util.Date
import java.util.UUID

data class BloodSugarRecord(
    val id: String = UUID.randomUUID().toString(),
    val period: String,
    val time: String,
    val value: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodGlucoseScreen() {
    var bloodSugarValue by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var selectedPeriod by remember { mutableStateOf("午前") }
    var selectedDate by remember { mutableStateOf(Date()) }

    val bloodSugarRecords = remember { mutableStateListOf<BloodSugarRecord>() }

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
                BloodSugarInput(
                    value = bloodSugarValue,
                    onValueChange = { bloodSugarValue = it },
                    modifier = Modifier.weight(1f)
                )

                TimeInputWithPicker(
                    time = selectedTime,
                    title = "測定時間",
                    onTimeChange = { selectedTime = it },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle(mainText = "測定期間")

            CustomSwitchButton(
                selectedOption = selectedPeriod,
                onOptionSelected = { selectedPeriod = it },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TodayBloodSugarValues(
                records = bloodSugarRecords,
                onDeleteRecord = { recordId ->
                    bloodSugarRecords.removeAll { it.id == recordId }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            MeasurementInfo()

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (bloodSugarValue.isNotBlank() && selectedTime.isNotBlank()) {
                        val newRecord = BloodSugarRecord(
                            period = selectedPeriod,
                            time = selectedTime,
                            value = "$bloodSugarValue mg/dL"
                        )
                        bloodSugarRecords.add(0, newRecord)

                        bloodSugarValue = ""
                        selectedTime = ""
                        selectedPeriod = "午前"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4285F4)
                ),
                shape = RoundedCornerShape(8.dp),
//                enabled = bloodSugarValue.isNotBlank() && selectedTime.isNotBlank()
            ) {
                Text(
                    text = "記録",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeInputWithPicker(
    time: String,
    title: String,
    onTimeChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showTimePicker by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        SectionTitle(mainText = title)

        CustomOutlinedTextField(
            value = time,
            onValueChange = { },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.clickable {
                showTimePicker = true
            },
            placeholder = "00:00",
            isEnable = false,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = "Time",
                )
            }

        )
    }

    if (showTimePicker) {
        TimePickerDialog(
            currentTime = time,
            onTimeSelected = { newTime ->
                onTimeChange(newTime)
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    currentTime: String,
    onTimeSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val timeParts = currentTime.split(":")
    val currentHour = timeParts.getOrNull(0)?.toIntOrNull() ?: 7
    val currentMinute = timeParts.getOrNull(1)?.toIntOrNull() ?: 0

    val timePickerState = rememberTimePickerState(
        initialHour = currentHour,
        initialMinute = currentMinute,
        is24Hour = true
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
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

                TimePicker(
                    state = timePickerState
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Gray
                        )
                    ) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    TextButton(
                        onClick = {
                            val selectedTime = String.format(
                                java.util.Locale.JAPANESE,
                                "%02d:%02d",
                                timePickerState.hour,
                                timePickerState.minute
                            )
                            onTimeSelected(selectedTime)
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color(0xFF4285F4)
                        )
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodSugarInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SectionTitle(
            mainText = "血糖",
            subText = " (mg/dL)"
        )

        CustomOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )
    }
}
@Composable
fun TodayBloodSugarValues(
    records: List<BloodSugarRecord>,
    onDeleteRecord: (String) -> Unit
) {
    Column {
        SectionTitle(
            mainText = "本日の血糖値",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (records.isEmpty()) {
            Text(
                text = "運動履歴はまだありません。",
                fontSize = 10.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.W400,
                color = Color.Gray
            )
        } else {
            records.forEachIndexed { index, record ->
                if (index > 0) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color(0xFFE0E0E0)
                    )
                }

                BloodSugarValueRow(
                    record = record,
                    onDelete = { onDeleteRecord(record.id) }
                )

                if (index == records.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun BloodSugarValueRow(
    record: BloodSugarRecord,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = record.period,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = record.time,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = record.value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(24.dp))
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onDelete() }
            )
        }
    }
}

@Composable
fun MeasurementInfo() {
    Column {
        Text(
            text = "測定時間について",
            fontSize = 14.sp,
            lineHeight = 16.sp,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.W500,
            color = TextBlue,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.measurement_time),
            contentDescription = "IMAGE",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    clip = true
                )
                .border(BorderStroke(1.dp, BorderColor), RoundedCornerShape(8.dp))
                .background(Color.White)

        )
    }
}

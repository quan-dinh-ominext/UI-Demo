package com.quan.homwork1.ui.pages
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccessTime
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.quan.homwork1.ui.common.SectionTitle
//import com.quan.homwork1.ui.components.common.DateNavigationBar
//import com.quan.homwork1.ui.components.buttons.SimpleSwitchButton
//import com.quan.homwork1.ui.components.buttons.TimePeriod
//import java.text.SimpleDateFormat
//import java.util.*
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BloodGlucoseScreen() {
//    var bloodSugarValue by remember { mutableStateOf("-") }
//    var selectedTime by remember { mutableStateOf("00:00") }
////    var selectedPeriod by remember { mutableStateOf("午前") }
//    var morningRecord by remember { mutableStateOf<BloodSugarRecord?>(null) }
//    var afternoonRecord by remember { mutableStateOf<BloodSugarRecord?>(null) }
//    var selectedDate by remember { mutableStateOf(Date()) }
//
//    val context = LocalContext.current
//    val calendar = Calendar.getInstance()
//    val dateFormat = SimpleDateFormat("yyyy/MM/dd (E)", Locale.JAPANESE)
//    val currentDate = dateFormat.format(calendar.time)
//    val mCheckedState = remember { mutableStateOf(false) }
//    var selectedPeriod by remember { mutableStateOf<TimePeriod>(TimePeriod.MORNING) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF5F5F5))
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .padding(16.dp)
//        ) {
//
//            DateNavigationBar(
//                currentDate = selectedDate,
//                onDateChanged = { selectedDate = it }
//            )
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Column(modifier = Modifier.weight(1f)) {
//                    SectionTitle(
//                        mainText = "血糖",
//                        subText = "(mg/dL)"
//                    )
//                    OutlinedTextField(
//                        value = bloodSugarValue,
//                        onValueChange = { bloodSugarValue = it },
//                        modifier = Modifier.fillMaxWidth(),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        singleLine = true,
////                        colors = OutlinedTextFieldDefaults.colors(
////                            focusedBorderColor = Color(0xFF4285F4),
////                            unfocusedBorderColor = Color.LightGray
////                        )
//                    )
//                }
//
//                Spacer(modifier = Modifier.width(16.dp))
//
//                Column(modifier = Modifier.weight(1f)) {
//
//                    SectionTitle(
//                        mainText = "測定時間",
//                    )
//
//                    OutlinedTextField(
//                        value = selectedTime,
//                        onValueChange = { selectedTime = it },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
//                                val timeParts = selectedTime.split(":")
//                                val hour = timeParts[0].toIntOrNull() ?: 7
//                                val minute = timeParts[1].toIntOrNull() ?: 0
//
////                                TimePickerDialog(
////                                    onTimeSelected = { hour, minute ->
////                                        selectedTime = String.format("%02d:%02d", hour, minute)
////                                    },
////                                    onDismiss = {  },
////                                    initialTime = selectedTime
////                                )
//                            },
//                        readOnly = false,
//                        singleLine = true,
//                        leadingIcon = {
//                            Icon(
//                                imageVector = Icons.Default.AccessTime,
//                                contentDescription = "Time",
//                                tint = Color.Gray
//                            )
//                        },
//                        colors = OutlinedTextFieldDefaults.colors(
//                            focusedBorderColor = Color(0xFF4285F4),
//                            unfocusedBorderColor = Color.LightGray
//                        )
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            SectionTitle(
//                mainText = "測定期間",
//            )
//
//            SimpleSwitchButton(
//                selectedPeriod = selectedPeriod,
//                onPeriodChange = { selectedPeriod = it },
//                modifier = Modifier.width(200.dp)
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            SectionTitle(
//                mainText = "本日の血糖値",
//            )
//
//            // Records Display
//            if (morningRecord != null || afternoonRecord != null) {
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = CardDefaults.cardColors(containerColor = Color.White),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        afternoonRecord?.let { record ->
//                            BloodSugarRecordItem(
//                                period = "午後",
//                                time = record.time,
//                                value = "${record.value} mg/dL"
//                            )
//                            if (morningRecord != null) {
//                                Spacer(modifier = Modifier.height(8.dp))
//                            }
//                        }
//
//                        morningRecord?.let { record ->
//                            BloodSugarRecordItem(
//                                period = "午前",
//                                time = record.time,
//                                value = "${record.value} mg/dL"
//                            )
//                        }
//                    }
//                }
//            } else {
//                Text(
//                    "測定履歴はありません。",
//                    fontSize = 14.sp,
//                    color = Color.Gray,
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Information Section
//            Text(
//                "測定時間について",
//                fontSize = 14.sp,
//                color = Color(0xFF4285F4),
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            // Reference Values
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                colors = CardDefaults.cardColors(containerColor = Color.White),
//                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text("📅", fontSize = 20.sp)
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(
//                            "血糖値（静脈血漿値）",
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    // Normal Range
//                    Button(
//                        onClick = { },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFF4285F4)
//                        ),
//                        shape = RoundedCornerShape(8.dp)
//                    ) {
//                        Text("糖尿病型", color = Color.White, fontWeight = FontWeight.Medium)
//                    }
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    ReferenceValueRow(
//                        label = "空腹時",
//                        normalRange = "126\nmg/dL 以上",
//                        postMealRange = "200\nmg/dL 以上"
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    Button(
//                        onClick = { },
//                        modifier = Modifier.fillMaxWidth(),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFF4285F4)
//                        ),
//                        shape = RoundedCornerShape(8.dp)
//                    ) {
//                        Text("正常型", color = Color.White, fontWeight = FontWeight.Medium)
//                    }
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    ReferenceValueRow(
//                        label = "空腹時",
//                        normalRange = "110\nmg/dL 未満",
//                        postMealRange = "140\nmg/dL 未満"
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Record Button
//            Button(
//                onClick = {
//                    if (bloodSugarValue.isNotEmpty()) {
//                        val record = BloodSugarRecord(
//                            value = bloodSugarValue.toIntOrNull() ?: 0,
//                            time = selectedTime,
//                            period = selectedPeriod
//                        )
//
//                        when (selectedPeriod) {
//                            TimePeriod.MORNING -> morningRecord = record
//                            TimePeriod.AFTERNOON -> afternoonRecord = record
//                        }
//
//                        // Clear input after recording
//                        bloodSugarValue = ""
//
//                        // Print the retrieved data (for demonstration)
//                        println("Recorded: $record")
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF4285F4)
//                ),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Text(
//                    "記録",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Color.White
//                )
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//        }
//    }
//}
//
//@Composable
//fun BloodSugarRecordItem(period: String, time: String, value: String) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            period,
//            fontSize = 14.sp,
//            color = Color.Black,
//            modifier = Modifier.weight(1f)
//        )
//        Text(
//            time,
//            fontSize = 14.sp,
//            color = Color.Gray,
//            modifier = Modifier.weight(1f),
//            textAlign = TextAlign.Center
//        )
//        Row(
//            modifier = Modifier.weight(1f),
//            horizontalArrangement = Arrangement.End,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                value,
//                fontSize = 14.sp,
//                color = Color.Black
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Icon(
//                Icons.Default.Delete,
//                contentDescription = "Delete",
//                tint = Color.Red,
//                modifier = Modifier.size(16.dp)
//            )
//        }
//    }
//}
//
//@Composable
//fun ReferenceValueRow(label: String, normalRange: String, postMealRange: String) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(
//            label,
//            fontSize = 12.sp,
//            color = Color.Gray,
//            modifier = Modifier.weight(1f)
//        )
//        Text(
//            "あまたはー",
//            fontSize = 12.sp,
//            color = Color.Gray,
//            modifier = Modifier.weight(1f),
//            textAlign = TextAlign.Center
//        )
//        Text(
//            normalRange,
//            fontSize = 12.sp,
//            color = Color.Gray,
//            modifier = Modifier.weight(1f),
//            textAlign = TextAlign.Center
//        )
//        Text(
//            "食後2時間",
//            fontSize = 12.sp,
//            color = Color.Gray,
//            modifier = Modifier.weight(1f),
//            textAlign = TextAlign.Center
//        )
//        Text(
//            postMealRange,
//            fontSize = 12.sp,
//            color = Color.Gray,
//            modifier = Modifier.weight(1f),
//            textAlign = TextAlign.End
//        )
//    }
//}
//
//data class BloodSugarRecord(
//    val value: Int,
//    val time: String,
//    val period: TimePeriod
//)


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quan.homwork1.ui.common.SectionTitle
import com.quan.homwork1.ui.components.buttons.SimpleSwitchButton
import com.quan.homwork1.ui.components.buttons.TimePeriod
import com.quan.homwork1.ui.components.common.DateNavigationBar
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

data class GlucoseReading(
    val time: LocalTime,
    val value: Int,
    val period: TimePeriod
)

//enum class TimePeriod(val displayName: String) {
//    MORNING("午前"),
//    AFTERNOON("午後")
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodGlucoseScreen() {
    var glucoseValue by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf(LocalTime.of(0, 0)) }
//    var selectedPeriod by remember { mutableStateOf(TimePeriod.MORNING) }
    var selectedPeriod by remember { mutableStateOf<TimePeriod>(TimePeriod.MORNING) }
    var dailyReadings by remember {
        mutableStateOf(listOf(
            GlucoseReading(LocalTime.of(17, 0), 105, TimePeriod.AFTERNOON),
            GlucoseReading(LocalTime.of(7, 0), 95, TimePeriod.MORNING)
        ))
    }
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(Date()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // Main Content
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                DateNavigationBar(
                    currentDate = selectedDate,
                    onDateChanged = { newDate ->
                        selectedDate = newDate
                    }
                )
            }
            item {
                // Input Section
                InputSection(
                    glucoseValue = glucoseValue,
                    onGlucoseValueChange = { glucoseValue = it },
                    selectedTime = selectedTime,
                    onTimeClick = { showTimePicker = true },
                    selectedPeriod = selectedPeriod,
                    onPeriodChange = { selectedPeriod = it }
                )
            }

            item {
                // Daily Values
                DailyValuesSection(
                    readings = dailyReadings,
                    onDeleteReading = { reading ->
                        dailyReadings = dailyReadings.filter { it != reading }
                    }
                )
            }

            item {
                // Reference Ranges
                ReferenceRangesSection()
            }

            item {
                Spacer(modifier = Modifier.height(80.dp)) // Space for FAB
            }
        }

        // Record Button
        RecordButton(
            onRecord = {
                if (glucoseValue.isNotEmpty()) {
                    val newReading = GlucoseReading(
                        time = selectedTime,
                        value = glucoseValue.toIntOrNull() ?: 0,
                        period = selectedPeriod
                    )
                    dailyReadings = (dailyReadings.filter { it.time != selectedTime } + newReading)
                        .sortedByDescending { it.time }
                    glucoseValue = ""
                }
            }
        )
    }

    // Time Picker Dialog
    if (showTimePicker) {
        TimePickerDialog(
            selectedTime = selectedTime,
            onTimeSelected = { selectedTime = it },
            onDismiss = { showTimePicker = false }
        )
    }
}

@Composable
fun InputSection(
    glucoseValue: String,
    onGlucoseValueChange: (String) -> Unit,
    selectedTime: LocalTime,
    onTimeClick: () -> Unit,
    selectedPeriod: TimePeriod,
    onPeriodChange: (TimePeriod) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Input fields
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "血糖 (mg/dL)",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = glucoseValue,
                    onValueChange = onGlucoseValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF8F9FA),
                        focusedContainerColor = Color.White,
                        unfocusedBorderColor = Color(0xFFE9ECEF),
                        focusedBorderColor = Color(0xFF007AFF)
                    )
                )
            }
            Column(modifier = Modifier.weight(1f)) {

                SectionTitle(mainText = "測定時間")

                OutlinedTextField(
                    value = selectedTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTimeClick() },
                    readOnly = true,
                    leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.AccessTime,
                                contentDescription = "Time",
                                tint = Color.Gray
                            )
                        },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF8F9FA),
                        focusedContainerColor = Color.White,
                        unfocusedBorderColor = Color(0xFFE9ECEF),
                        focusedBorderColor = Color(0xFF007AFF)
                    )
                )
            }
        }

            SectionTitle(
                mainText = "測定期間",
            )

            SimpleSwitchButton(
                selectedPeriod = selectedPeriod,
                onPeriodChange = { selectedPeriod = it },
                modifier = Modifier.width(200.dp)
            )
    }
}

@Composable
fun DailyValuesSection(
    readings: List<GlucoseReading>,
    onDeleteReading: (GlucoseReading) -> Unit
) {
    Column {
        Text(
            text = "本日の血糖値",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (readings.isEmpty()) {
            Text(
                text = "記録値はありません。",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
        } else {
            readings.forEach { reading ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = reading.period.displayName,
                        fontSize = 14.sp,
                        color = Color(0xFF666666)
                    )
                    Text(
                        text = reading.time.format(DateTimeFormatter.ofPattern("HH:mm")),
                        fontSize = 14.sp,
                        color = Color(0xFF666666)
                    )
                    Text(
                        text = "${reading.value} mg/dL",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    IconButton(
                        onClick = { onDeleteReading(reading) },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "削除",
                            tint = Color(0xFFFF3B30),
                            modifier = Modifier.size(18.sp.value.dp)
                        )
                    }
                }
                if (reading != readings.last()) {
                    Divider(color = Color(0xFFF0F0F0))
                }
            }
        }

        Text(
            text = "測定時間について",
            fontSize = 14.sp,
            color = Color(0xFF007AFF),
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { }
        )
    }
}

@Composable
fun ReferenceRangesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F9FA), RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color(0xFF007AFF), RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("📅", fontSize = 12.sp)
            }
            Text(
                text = "血糖値（静脈血漿値）",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
        }

        // Diabetes range
        ReferenceRangeCard(
            title = "糖尿病型",
            leftValue = "126",
            leftUnit = "mg/dL以上",
            rightValue = "200",
            rightUnit = "mg/dL以上",
            backgroundColor = Color(0xFF007AFF)
        )

        // Normal range
        ReferenceRangeCard(
            title = "正常型",
            leftValue = "110",
            leftUnit = "mg/dL未満",
            rightValue = "140",
            rightUnit = "mg/dL未満",
            backgroundColor = Color(0xFF34C759),
            separator = "——かつ——"
        )
    }
}

@Composable
fun ReferenceRangeCard(
    title: String,
    leftValue: String,
    leftUnit: String,
    rightValue: String,
    rightUnit: String,
    backgroundColor: Color,
    separator: String = "——あるいは——"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = leftValue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = leftUnit,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }

            Text(
                text = separator,
                fontSize = 12.sp,
                color = Color.White
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = rightValue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = rightUnit,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun RecordButton(onRecord: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Button(
            onClick = onRecord,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007AFF),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "記録",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    selectedTime: LocalTime,
    onTimeSelected: (LocalTime) -> Unit, // This callback is used to update the state
    onDismiss: () -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = selectedTime.hour,
        initialMinute = selectedTime.minute
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    // FIX: Use the onTimeSelected callback to pass the new value up.
                    onTimeSelected(LocalTime.of(timePickerState.hour, timePickerState.minute))
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("キャンセル")
            }
        },
        text = {
            TimePicker(state = timePickerState)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BloodGlucoseTrackerPreview() {
    MaterialTheme {
        BloodGlucoseScreen()
    }
}
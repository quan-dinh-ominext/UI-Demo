@file:OptIn(ExperimentalMaterial3Api::class)

package com.quan.homwork1.ui.pages

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodSugarTrackerScreen() {
    var bloodSugarValue by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("07:00") }
    var selectedPeriod by remember { mutableStateOf("午前") }
    var morningRecord by remember { mutableStateOf<BloodSugarRecord?>(null) }
    var afternoonRecord by remember { mutableStateOf<BloodSugarRecord?>(null) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy/MM/dd (E)", Locale.JAPANESE)
    val currentDate = dateFormat.format(calendar.time)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    "記録画面",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* Handle back navigation */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )

        // Tab Row
        ScrollableTabRow(
            selectedTabIndex = 3, // Blood sugar tab selected
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
            contentColor = Color.Gray,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[3]),
                    color = Color(0xFF4285F4)
                )
            }
        ) {
            val tabs = listOf("腹囲", "睡眠", "血圧", "血糖", "飲酒", "喫煙")
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            title,
                            color = if (index == 3) Color(0xFF4285F4) else Color.Gray,
                            fontWeight = if (index == 3) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    selected = index == 3,
                    onClick = { }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Date Navigation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("<", fontSize = 20.sp, color = Color.Gray)
                Text(
                    "$currentDate • 本日",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(">", fontSize = 20.sp, color = Color.Gray)
            }

            // Blood Sugar Input Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Input Fields Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Blood Sugar Input
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "血糖 (mg/dL)",
                                fontSize = 14.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            OutlinedTextField(
                                value = bloodSugarValue,
                                onValueChange = { bloodSugarValue = it },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF4285F4),
                                    unfocusedBorderColor = Color.LightGray
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Time Input
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "測定時間",
                                fontSize = 14.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            OutlinedTextField(
                                value = selectedTime,
                                onValueChange = { selectedTime = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        val timeParts = selectedTime.split(":")
                                        val hour = timeParts[0].toIntOrNull() ?: 7
                                        val minute = timeParts[1].toIntOrNull() ?: 0

                                        TimePickerDialog(
                                            context,
                                            { _, h, m ->
                                                selectedTime = String.format("%02d:%02d", h, m)
                                            },
                                            hour,
                                            minute,
                                            true
                                        ).show()
                                    },
                                readOnly = false,
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Time",
                                        tint = Color.Gray
                                    )
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF4285F4),
                                    unfocusedBorderColor = Color.LightGray
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Period Selection
                    Text(
                        "測定期間",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            onClick = { selectedPeriod = "午前" },
                            label = { Text("午前") },
                            selected = selectedPeriod == "午前",
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(0xFF4285F4),
                                selectedLabelColor = Color.White
                            )
                        )
                        FilterChip(
                            onClick = { selectedPeriod = "午後" },
                            label = { Text("午後") },
                            selected = selectedPeriod == "午後",
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color.Gray,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Today's Blood Sugar Values
            Text(
                "本日の血糖値",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Records Display
            if (morningRecord != null || afternoonRecord != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        afternoonRecord?.let { record ->
                            BloodSugarRecordItem(
                                period = "午後",
                                time = record.time,
                                value = "${record.value} mg/dL"
                            )
                            if (morningRecord != null) {
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }

                        morningRecord?.let { record ->
                            BloodSugarRecordItem(
                                period = "午前",
                                time = record.time,
                                value = "${record.value} mg/dL"
                            )
                        }
                    }
                }
            } else {
                Text(
                    "測定履歴はありません。",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Information Section
            Text(
                "測定時間について",
                fontSize = 14.sp,
                color = Color(0xFF4285F4),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Reference Values
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("📅", fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "血糖値（静脈血漿値）",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Normal Range
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4285F4)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("糖尿病型", color = Color.White, fontWeight = FontWeight.Medium)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    ReferenceValueRow(
                        label = "空腹時",
                        normalRange = "126\nmg/dL 以上",
                        postMealRange = "200\nmg/dL 以上"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4285F4)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("正常型", color = Color.White, fontWeight = FontWeight.Medium)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    ReferenceValueRow(
                        label = "空腹時",
                        normalRange = "110\nmg/dL 未満",
                        postMealRange = "140\nmg/dL 未満"
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Record Button
            Button(
                onClick = {
                    if (bloodSugarValue.isNotEmpty()) {
                        val record = BloodSugarRecord(
                            value = bloodSugarValue.toIntOrNull() ?: 0,
                            time = selectedTime,
                            period = selectedPeriod
                        )

                        when (selectedPeriod) {
                            "午前" -> morningRecord = record
                            "午後" -> afternoonRecord = record
                        }

                        // Clear input after recording
                        bloodSugarValue = ""

                        // Print the retrieved data (for demonstration)
                        println("Recorded: $record")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4285F4)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "記録",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun BloodSugarRecordItem(period: String, time: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            period,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Text(
            time,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                value,
                fontSize = 14.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun ReferenceValueRow(label: String, normalRange: String, postMealRange: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.weight(1f)
        )
        Text(
            "あまたはー",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            normalRange,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            "食後2時間",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            postMealRange,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }
}

data class BloodSugarRecord(
    val value: Int,
    val time: String,
    val period: String
)
package com.quan.homwork1.ui.pages

// SleepTrackingScreen.kt
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quan.homwork1.R
import com.quan.homwork1.data.ChartData
import com.quan.homwork1.data.SleepRecord
import com.quan.homwork1.data.SleepStatistics
import com.quan.homwork1.data.TimeFilter
import com.quan.homwork1.ui.components.common.SleepChart
import com.quan.homwork1.ui.theme.BackgroundColorSwitch
import com.quan.homwork1.ui.theme.ColorUnselected
import com.quan.homwork1.ui.theme.TextPrimary
import com.quan.homwork1.ui.theme.TextSecondary
import com.quan.homwork1.ui.viewmodel.SleepViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SleepDetailScreen(
    viewModel: SleepViewModel = viewModel()
) {
//    val selectedTimeFilter by viewModel.selectedTimeFilter.collectAsState()
//    val currentPeriod by viewModel.currentPeriod.collectAsState()
//    val chartData by viewModel.chartData.collectAsState()
//    val sleepStatistics by viewModel.sleepStatistics.collectAsState()
//    val sleepRecords by viewModel.sleepRecords.collectAsState()
//    val expandedRecord by viewModel.expandedRecord.collectAsState()

    val selectedTimeFilter by viewModel.selectedTimeFilter.collectAsState()
    val currentPeriod by viewModel.currentPeriod.collectAsState()
    val chartEntries by viewModel.chartEntries.collectAsState()
    val chartLabels by viewModel.chartLabels.collectAsState()
    val sleepStatistics by viewModel.sleepStatistics.collectAsState()
    val sleepRecords by viewModel.sleepRecords.collectAsState()
    val expandedRecord by viewModel.expandedRecord.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TimeFilterSection(
                selectedFilter = selectedTimeFilter,
                onFilterSelected = { viewModel.selectTimeFilter(it) }
            )
        }

        item {
            PeriodNavigationSection(
                currentPeriod = currentPeriod,
                selectedTimeFilter = selectedTimeFilter,
                onPrevious = { viewModel.navigatePrevious() },
                onNext = { viewModel.navigateNext() }
            )
        }

        item {
            SleepSummarySection(statistics = sleepStatistics)
        }

        item {
            // Updated to use MPAndroidChart
            SleepChart(
                entries = chartEntries,
                labels = chartLabels,
                timeFilter = selectedTimeFilter
            )
        }

//        item {
//            SleepChartSection(
//                data = chartData,
//                timeFilter = selectedTimeFilter
//            )
//        }

        item {
            StatisticsSection(statistics = sleepStatistics)
        }

        items(sleepRecords) { record ->
            SleepRecordItem(
                record = record,
                isExpanded = expandedRecord == record.date,
                onToggleExpansion = { viewModel.toggleRecordExpansion(record.date) }
            )
        }

        item {
            HealthConnectSection()
        }
    }
}

@Composable
fun TimeFilterSection(
    selectedFilter: TimeFilter,
    onFilterSelected: (TimeFilter) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundColorSwitch),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            TimeFilter.entries.forEach { filter ->
                val isSelected = selectedFilter == filter
                val text = when (filter) {
                    TimeFilter.WEEK -> "週"
                    TimeFilter.MONTH -> "月"
                    TimeFilter.YEAR -> "年"
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onFilterSelected(filter) }
                        .background(
                            if (isSelected) Color.White else BackgroundColorSwitch,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        color = if (isSelected) TextPrimary else ColorUnselected,
                        fontWeight = if (isSelected) FontWeight.W700 else FontWeight.W500,
                        fontSize = 14.sp,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PeriodNavigationSection(
    currentPeriod: String,
    onPrevious: () -> Unit,
    selectedTimeFilter: TimeFilter,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPrevious) {
            Icon(
                painter = painterResource(R.drawable.ic_angle_left),
                contentDescription = "Previous",
                tint = TextSecondary
            )
        }

        Text(
            text = currentPeriod,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.W700,
            color = TextSecondary
        )

        IconButton(onClick = onNext) {
            Icon(
                painter = painterResource(R.drawable.ic_angle_right),
                contentDescription = "Previous",
                tint = TextSecondary
            )
        }
    }
}

@Composable
fun SleepSummarySection(statistics: SleepStatistics) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "平均睡眠時間",
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 16.sp,
            color = TextSecondary
        )

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = TextPrimary,
                        fontWeight = FontWeight.W700,
                        fontSize = 32.sp
                    )
                ) {
                    append("8")
                }

                withStyle(
                    style = SpanStyle(
                        color = TextPrimary,
                        fontWeight = FontWeight.W700,
                        fontSize = 14.sp
                    )
                ) {
                    append("h")
                }

                withStyle(
                    style = SpanStyle(
                        color = TextPrimary,
                        fontWeight = FontWeight.W700,
                        fontSize = 32.sp
                    )
                ) {
                    append("30")
                }

                withStyle(
                    style = SpanStyle(
                        color = TextPrimary,
                        fontWeight = FontWeight.W700,
                        fontSize = 14.sp
                    )
                ) {
                    append("m")
                }

            }
        )
        Text(
            text = "睡眠目標 ${statistics.sleepGoal}",
            fontSize = 13.sp,
            lineHeight = 16.sp,
            color = TextSecondary,
            fontWeight = FontWeight.W400,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun SleepChartSection(
    data: List<ChartData>,
    timeFilter: TimeFilter
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
        ) {
            // Simple bar chart implementation
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                data.forEach { chartPoint ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .height((chartPoint.value * 8).dp)
                                .background(
                                    Color(0xFF9C27B0),
                                    RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
                                )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = chartPoint.label,
                            fontSize = 10.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatisticsSection(statistics: SleepStatistics) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            StatisticRow(
                icon = Icons.Default.Bedtime,
                label = "平均睡眠時間",
                value = statistics.averageSleepTime
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            StatisticRow(
                icon = Icons.Default.NightsStay,
                label = "平均就寝時間",
                value = statistics.averageBedtime
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            StatisticRow(
                icon = Icons.Default.WbSunny,
                label = "平均起床時間",
                value = statistics.averageWakeupTime
            )
        }
    }
}

@Composable
fun StatisticRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun SleepRecordItem(
    record: SleepRecord,
    isExpanded: Boolean,
    onToggleExpansion: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleExpansion() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = record.date,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = record.sleepDuration)
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Bedtime,
                        contentDescription = null,
                        tint = Color(0xFF9C27B0),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = record.sleepDuration,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF9C27B0)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                DetailRow("睡眠の質", record.sleepQuality)
                DetailRow("起床時間", record.wakeupTime)
                DetailRow("前日の就寝時間", record.bedtime)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Handle edit */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE3F2FD),
                        contentColor = Color(0xFF1976D2)
                    )
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("データを編集")
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Text(
            text = value,
            fontSize = 14.sp
        )
    }
}

@Composable
fun HealthConnectSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { /* Handle health connect */ },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color(0xFF1976D2),
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "ヘルスコネクトと連携すると、歩数や睡眠データが自動記録され、健康管理が簡単になります。",
                fontSize = 12.sp,
                color = Color(0xFF1976D2),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color(0xFF1976D2)
            )
        }
    }
}
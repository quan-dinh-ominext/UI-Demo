package com.quan.homwork1.ui.components.common

// SleepChart.kt
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.quan.homwork1.data.TimeFilter

//@Composable
//fun SleepChart(
//    entries: List<BarEntry>,
//    labels: List<String>,
//    timeFilter: TimeFilter,
//    modifier: Modifier = Modifier
//) {
//    val context = LocalContext.current
//
//    Card(
//        modifier = modifier.fillMaxWidth(),
//        colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White),
//        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(250.dp)
//                .padding(16.dp)
//        ) {
//            AndroidView(
//                factory = { context ->
//                    BarChart(context).apply {
//                        setupChartWithGoalLine(this, entries, labels, timeFilter)
//                    }
//                },
//                update = { chart ->
////                    setupChart(chart, entries, labels, timeFilter)
//                    setupChartWithGoalLine(chart, entries, labels, timeFilter)
//                },
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}
//
//private fun setupChart(
//    chart: BarChart,
//    entries: List<BarEntry>,
//    labels: List<String>,
//    timeFilter: TimeFilter
//) {
//    // Create dataset
//    val dataSet = BarDataSet(entries, "Sleep Hours").apply {
//        color = Color(0xFF9C27B0).toArgb() // Purple color matching the design
//        setDrawValues(false)
//        barBorderWidth = 0f
//    }
//
//    // Create bar data
//    val barData = BarData(dataSet).apply {
//        barWidth = when (timeFilter) {
//            TimeFilter.WEEK -> 0.6f
//            TimeFilter.MONTH -> 0.8f
//            TimeFilter.YEAR -> 0.7f
//        }
//    }
//
//    chart.apply {
//        data = barData
//
//        // Configure chart appearance
//        description.isEnabled = false
//        legend.isEnabled = false
//        setDrawGridBackground(false)
//        setDrawBorders(false)
//        setScaleEnabled(false)
//        setPinchZoom(false)
//        setDoubleTapToZoomEnabled(false)
//
//        // Configure X-axis
//        xAxis.apply {
//            position = XAxis.XAxisPosition.BOTTOM
//            setDrawGridLines(false)
//            setDrawAxisLine(false)
//            granularity = 1f
//            labelCount = labels.size
//            valueFormatter = IndexAxisValueFormatter(labels)
//            textSize = 10f
//            textColor = Color(0xFF666666).toArgb()
//        }
//
//        // Configure left Y-axis
//        axisLeft.apply {
//            setDrawGridLines(true)
//            setDrawAxisLine(false)
//            gridColor = Color(0xFFE0E0E0).toArgb()
//            gridLineWidth = 1f
//            enableGridDashedLine(10f, 5f, 0f)
//            axisMinimum = 0f
//            axisMaximum = 24f
//            labelCount = 7
//            textSize = 10f
//            textColor = Color(0xFF666666).toArgb()
//            valueFormatter = object : ValueFormatter() {
//                override fun getFormattedValue(value: Float): String {
//                    return if (value == 0f) "" else "${value.toInt()}"
//                }
//            }
//        }
//
//        // Configure right Y-axis
//        axisRight.isEnabled = false
//
//        // Set margins
//        setExtraOffsets(10f, 20f, 10f, 10f)
//
//        // Animate chart
//        animateY(1000)
//
//        // Refresh chart
//        invalidate()
//    }
//}
//
//// Custom ValueFormatter for sleep hours
//class SleepHoursFormatter : ValueFormatter() {
//    override fun getFormattedValue(value: Float): String {
//        return if (value > 0) {
//            val hours = value.toInt()
//            val minutes = ((value - hours) * 60).toInt()
//            if (minutes > 0) "${hours}h ${minutes}m" else "${hours}h"
//        } else ""
//    }
//}
//
//private fun setupChartWithGoalLine(
//    chart: BarChart,
//    entries: List<BarEntry>,
//    labels: List<String>,
//    timeFilter: TimeFilter,
//    goalValue: Float = 8f // 8 hours goal
//) {
//    // Create dataset
//    val dataSet = BarDataSet(entries, "Sleep Hours").apply {
//        color = Color(0xFF9C27B0).toArgb()
//        setDrawValues(false)
//        barBorderWidth = 0f
//    }
//
//    // Create bar data
//    val barData = BarData(dataSet).apply {
//        barWidth = when (timeFilter) {
//            TimeFilter.WEEK -> 0.6f
//            TimeFilter.MONTH -> 0.8f
//            TimeFilter.YEAR -> 0.7f
//        }
//    }
//
//    chart.apply {
//        data = barData
//
//        // Configure chart appearance
//        description.isEnabled = false
//        legend.isEnabled = false
//        setDrawGridBackground(false)
//        setDrawBorders(false)
//        setScaleEnabled(false)
//        setPinchZoom(false)
//        setDoubleTapToZoomEnabled(false)
//
//        // Add limit line for goal
//        axisLeft.apply {
//            removeAllLimitLines()
//            val goalLine = com.github.mikephil.charting.components.LimitLine(goalValue, "").apply {
//                lineColor = Color(0xFF1976D2).toArgb()
//                lineWidth = 2f
//                enableDashedLine(10f, 5f, 0f)
//                labelPosition = com.github.mikephil.charting.components.LimitLine.LimitLabelPosition.RIGHT_TOP
//                textSize = 10f
//                textColor = Color(0xFF1976D2).toArgb()
//            }
//            addLimitLine(goalLine)
//        }
//
//
//
//        // Configure X-axis
//        xAxis.apply {
//            position = XAxis.XAxisPosition.BOTTOM
//            setDrawGridLines(false)
//            setDrawAxisLine(false)
//            granularity = 1f
//            labelCount = labels.size
//            valueFormatter = IndexAxisValueFormatter(labels)
//            textSize = 10f
//            textColor = Color(0xFF666666).toArgb()
//        }
//
//
//        // Configure left Y-axis
//        axisLeft.apply {
//            setDrawGridLines(true)
//            setDrawAxisLine(false)
//            gridColor = Color(0xFFE0E0E0).toArgb()
//            gridLineWidth = 1f
//            enableGridDashedLine(10f, 5f, 0f)
//            axisMinimum = 0f
//            axisMaximum = 24f
//            labelCount = 7
//            textSize = 10f
//            textColor = Color(0xFF666666).toArgb()
//        }
//
//        // Configure right Y-axis
//        axisRight.isEnabled = false
//
////        axisRight.apply {
////            setDrawGridLines(true)
////            setDrawAxisLine(false)
////            gridColor = Color(0xFFE0E0E0).toArgb()
////            gridLineWidth = 1f
////            enableGridDashedLine(10f, 5f, 0f)
////            axisMinimum = 0f
////            axisMaximum = 24f
////            labelCount = 7
////            textSize = 10f
//////            textColor = Color(0xFF666666).toArgb()
////        }
//
//        // Set margins
//        setExtraOffsets(10f, 20f, 10f, 10f)
//
//        // Animate chart
//        animateY(1000)
//
//        // Refresh chart
//        invalidate()
//    }
//}


import com.github.mikephil.charting.components.LimitLine


// Updated SleepChart.kt to use custom chart
// SleepChart.kt
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card

import androidx.compose.runtime.*

import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun SleepChart(
    entries: List<BarEntry>,
    labels: List<String>,
    timeFilter: TimeFilter,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
//            .padding(16.dp)
    ) {
        AndroidView(
            factory = { context ->
                CustomBarChart(context).apply {
                    setupChartWithGradient(this, entries, labels, timeFilter)
                }
            },
            update = { chart ->
                setupChartWithGradient(chart, entries, labels, timeFilter)
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

private fun setupChartWithGradient(
    chart: BarChart,
    entries: List<BarEntry>,
    labels: List<String>,
    timeFilter: TimeFilter
) {

    val dataSet = BarDataSet(entries, "Sleep Hours").apply {

        color = Color(0xFF8979FF).toArgb()
        setDrawValues(false)
        barBorderWidth = 0f
    }

    val barData = BarData(dataSet).apply {
        barWidth = when (timeFilter) {
            TimeFilter.WEEK -> 0.6f
            TimeFilter.MONTH -> 0.4f
            TimeFilter.YEAR -> 0.7f
        }
    }

    chart.apply {
        data = barData

        description.isEnabled = false
        legend.isEnabled = false
        setDrawGridBackground(false)
        setDrawBorders(true)
//        setBorderColor(Color(0xFF2196F3).toArgb())
//        setBorderWidth(2f)
        setScaleEnabled(false)
        setPinchZoom(false)
        setDoubleTapToZoomEnabled(false)


        setDrawBorders(false)




        // Configure X-axis
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            setDrawAxisLine(false)
            granularity = 1f
            labelCount = labels.size
            valueFormatter = IndexAxisValueFormatter(labels)
            textSize = 10f
            textColor = Color(0xFF666666).toArgb()
        }

        // Configure left Y-axis
        axisLeft.apply {
            setDrawGridLines(true)
            setDrawAxisLine(false)
            gridColor = Color(0xFFE0E0E0).toArgb()
            gridLineWidth = 1f
            enableGridDashedLine(10f, 5f, 0f)
            axisMinimum = 0f
            axisMaximum = 24f
            labelCount = 7
            textSize = 10f
            textColor = Color(0xFF666666).toArgb()

            removeAllLimitLines()

            // Add goal line (8 hours)
            val goalLine = LimitLine(8f, "").apply {
                lineColor = Color(0xFF2196F3).toArgb()
                lineWidth = 2f
                enableDashedLine(10f, 5f, 0f)
            }
            addLimitLine(goalLine)
            setDrawLimitLinesBehindData(true)
        }

        axisRight.isEnabled = false
        setExtraOffsets(10f, 20f, 10f, 10f)
        animateY(1000)
        invalidate()
    }
}
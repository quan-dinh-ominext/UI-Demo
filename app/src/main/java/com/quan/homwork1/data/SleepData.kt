package com.quan.homwork1.data

data class SleepRecord(
    val date: String,
    val sleepDuration: String,
    val sleepQuality: String,
    val bedtime: String,
    val wakeupTime: String,
    val sleepHours: Float
)

data class SleepStatistics(
    val averageSleepTime: String,
    val averageBedtime: String,
    val averageWakeupTime: String,
    val sleepGoal: String
)

enum class TimeFilter {
    WEEK, MONTH, YEAR
}

data class ChartData(
    val label: String,
    val value: Float,
    val date: String = ""
)
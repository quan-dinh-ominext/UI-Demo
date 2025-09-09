package com.quan.homwork1.data


import androidx.annotation.StringRes
import com.quan.homwork1.R
import java.util.Calendar

enum class Period(@StringRes val titleResId: Int) {
    DAY(R.string.period_day),
    WEEK(R.string.period_week),
    MONTH(R.string.period_month),
    YEAR(R.string.period_year)
}

data class ChartEntry(
    val value: Float,
    val label: String
)

data class StepDetail(
    val date: Calendar,
    val steps: Int,
    val goal: Int,
    val calories: Int,
    val distanceMeters: Int,
    val walkingTimeMinutes: Int
)

data class CalorieDetail(
    val date: Calendar,
    val calories: Int,
    val protein: Int,
    val carbonHydrates: Int,
    val lipid: Int,
    val salt: Int,
    val goal: Int
)

data class WeightDetail(
    val date: Calendar,
    val weight: Float,
    val bmi: Float,
    val bodyFat: Float,
    val goal: Float,
)

data class AbdomenDetail(
    val date: Calendar,
    val goal: Float,
    val abdomen: Float
)

data class SleepDetail(
    val date: Calendar,
    val avgSleepHours: Float, // 平均睡眠時間
    val sleepEfficiency: Float, // 睡眠効率 (percentage)
    val sleepDuration: Float, // 睡眠時間 (hours)
    val bedTime: String, // 就寝時間 (HH:mm)
    val wakeUpTime: String // 起床時間 (HH:mm)
)

data class BloodSugar(
    val time: String, // Thời gian đo (HH:mm)
    val bloodSugarValue: Int, // Giá trị đo (mg/dL)
    val heartRate: Int // Giá trị đo (bpm)
)

data class BloodSugarDetail(
    val date: Calendar,
    val heartRate: Int, // Giá trị đo (bpm)
    val bloodSugar: Int, // 平均血糖値 (mg/dL)
    val goal: Int,
    val morningBloodSugar: Int, // 平均午前の血糖値 (mg/dL)
    val afternoonBloodSugar: Int, // 平均午後の血糖値 (mg/dL)
    val bloodSugars: List<BloodSugar> // Danh sách các lần đo trong ngày
)

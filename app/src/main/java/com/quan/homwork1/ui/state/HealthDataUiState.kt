//package com.quan.homwork1.ui.state
//
//import java.text.SimpleDateFormat
//import java.util.Calendar
//import java.util.Locale
//
//data class HealthDataUiState(
//    val tabs: List<String> = listOf("歩数", "食事", "体重", "腹囲", "睡眠", "血圧", "血糖", "飲酒", "喫煙", "生活習慣"),
//    val selectedTabIndex: Int = 0,
//    val periods: List<Period> = Period.values().toList(),
//    val selectedPeriod: Period = Period.DAY, // Đổi mặc định sang tab ngày
//    val currentDate: Calendar = Calendar.getInstance(),
//    val chartData: List<ChartEntry> = emptyList(),
//    val stepDetails: List<StepDetail> = emptyList(),
//    val totalSteps: Long = 0,
//    val averageSteps: Int = 0,
//    val averageCalories: Int = 0,
//    val averageDistanceMeters: Int = 0,
//    val averageWalkingTimeMinutes: Int = 0,
//    val stepGoal: Int = 10000, // Thêm mục tiêu số bước
//    // Calorie tab state
//    val caloriePeriods: List<Period> = Period.values().toList(),
//    val selectedCaloriePeriod: Period = Period.DAY,
//    val selectedAbdomenPeriod: Period = Period.DAY,
//    val selectedSleepPeriod: Period = Period.DAY,
//    val selectedBloodSugarPeriod: Period = Period.DAY,
//    val calorieChartData: List<ChartEntry> = emptyList(),
//    val calorieDetails: List<CalorieDetail> = emptyList(),
//    val totalCalories: Long = 0,
//    val averageCalorie: Int = 0,
//    val calorieGoal: Int = 2000,
//    val averageProtein: Int = 0,
//    val averageSalt: Int = 0,
//    val averageCarbonHydrates: Int = 0,
//    val averageLipid: Int = 0,
//    // Weight tab state
//    val weightPeriods: List<Period> = Period.values().toList(),
//    val selectedWeightPeriod: Period = Period.DAY,
//    val weightChartData: List<ChartEntry> = emptyList(),
//    val weightDetails: List<WeightDetail> = emptyList(),
//    val abdomenChartData: List<ChartEntry> = emptyList(),
//    val abdomenDetails: List<AbdomenDetail> = emptyList(),
//    val sleepDetails: List<SleepDetail> = emptyList(),
//    val bloodSugarDetails: List<BloodSugarDetail> = emptyList(),
//    val averageWeight: Float = 0f,
//    val averageBMI: Float = 0f,
//    val averageBodyFat: Float = 0f,
//    val targetWeight: Float = 60f, // Thêm mục tiêu cân nặng
//    val expandedWeightItemId: Calendar? = null, // Lưu trạng thái expand/collapse
//    val averageAbdomen: Float = 0f,
//    val targetAbdomen: Float = 80f,
//    val abdomenPeriods: List<Period> = Period.values().toList(),
//    val sleepPeriods: List<Period> = Period.values().toList(),
//    val sleepChartData: List<ChartEntry> = emptyList(),
//    val bloodSugarPeriods: List<Period> = Period.values().toList(),
//    val bloodSugarChartData: List<ChartEntry> = emptyList(),
//    val averageSleepHours: Float = 0f,
//    val averageSleepEfficiency: Float = 0f,
//    val averageSleepDuration: Float = 0f,
//    val heartRate: Int = 0,
//    val averageSystolic: Int = 0,
//    val averageDiastolic: Int = 0,
//    val averageBedTime: String = "20:00",
//    val averageWakeUpTime: String = "6:00",
//) {
//    val formattedDateRange: String
//        get() {
//            return when (selectedPeriod) {
//                Period.DAY -> SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(currentDate.time)
//                Period.WEEK -> {
//                    val weekStart = currentDate.clone() as Calendar
//                    weekStart.firstDayOfWeek = Calendar.MONDAY
//                    weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
//                    val weekEnd = weekStart.clone() as Calendar
//                    weekEnd.add(Calendar.DAY_OF_YEAR, 6)
//                    val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
//                    "${formatter.format(weekStart.time)} ~ ${formatter.format(weekEnd.time)}"
//                }
//                Period.MONTH -> SimpleDateFormat("yyyy/MM", Locale.getDefault()).format(currentDate.time)
//                Period.YEAR -> SimpleDateFormat("yyyy", Locale.getDefault()).format(currentDate.time)
//            }
//        }
//    val formattedWeightDateRange: String
//        get() {
//            return when (selectedWeightPeriod) {
//                Period.DAY -> ""
//                Period.WEEK -> {
//                    val weekStart = currentDate.clone() as Calendar
//                    weekStart.firstDayOfWeek = Calendar.MONDAY
//                    weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
//                    val weekEnd = weekStart.clone() as Calendar
//                    weekEnd.add(Calendar.DAY_OF_YEAR, 6)
//                    val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
//                    "${formatter.format(weekStart.time)} ~ ${formatter.format(weekEnd.time)}"
//                }
//                Period.MONTH -> SimpleDateFormat("yyyy/MM", Locale.getDefault()).format(currentDate.time)
//                Period.YEAR -> SimpleDateFormat("yyyy", Locale.getDefault()).format(currentDate.time)
//            }
//        }
//    val formattedCalorieDateRange: String
//        get() {
//            return when (selectedCaloriePeriod) {
//                Period.DAY -> ""
//                Period.WEEK -> {
//                    val weekStart = currentDate.clone() as Calendar
//                    weekStart.firstDayOfWeek = Calendar.MONDAY
//                    weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
//                    val weekEnd = weekStart.clone() as Calendar
//                    weekEnd.add(Calendar.DAY_OF_YEAR, 6)
//                    val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
//                    "${formatter.format(weekStart.time)} ~ ${formatter.format(weekEnd.time)}"
//                }
//                Period.MONTH -> SimpleDateFormat("yyyy/MM", Locale.getDefault()).format(currentDate.time)
//                Period.YEAR -> SimpleDateFormat("yyyy", Locale.getDefault()).format(currentDate.time)
//            }
//        }
//
//    val formattedAbdomenDateRange: String
//        get() {
//            return when (selectedAbdomenPeriod) {
//                Period.DAY -> ""
//                Period.WEEK -> {
//                    val weekStart = currentDate.clone() as Calendar
//                    weekStart.firstDayOfWeek = Calendar.MONDAY
//                    weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
//                    val weekEnd = weekStart.clone() as Calendar
//                    weekEnd.add(Calendar.DAY_OF_YEAR, 6)
//                    val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
//                    "${formatter.format(weekStart.time)} ~ ${formatter.format(weekEnd.time)}"
//                }
//                Period.MONTH -> SimpleDateFormat("yyyy/MM", Locale.getDefault()).format(currentDate.time)
//                Period.YEAR -> SimpleDateFormat("yyyy", Locale.getDefault()).format(currentDate.time)
//            }
//        }
//
//    val formattedSleepDateRange: String
//        get() {
//            return when (selectedSleepPeriod) {
//                Period.DAY -> ""
//                Period.WEEK -> {
//                    val weekStart = currentDate.clone() as Calendar
//                    weekStart.firstDayOfWeek = Calendar.MONDAY
//                    weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
//                    val weekEnd = weekStart.clone() as Calendar
//                    weekEnd.add(Calendar.DAY_OF_YEAR, 6)
//                    val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
//                    "${formatter.format(weekStart.time)} ~ ${formatter.format(weekEnd.time)}"
//                }
//                Period.MONTH -> SimpleDateFormat("yyyy/MM", Locale.getDefault()).format(currentDate.time)
//                Period.YEAR -> SimpleDateFormat("yyyy", Locale.getDefault()).format(currentDate.time)
//            }
//        }
//
//    val formattedBloodSugarDateRange: String
//        get() {
//            return when (selectedBloodSugarPeriod) {
//                Period.DAY -> ""
//                Period.WEEK -> {
//                    val weekStart = currentDate.clone() as Calendar
//                    weekStart.firstDayOfWeek = Calendar.MONDAY
//                    weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
//                    val weekEnd = weekStart.clone() as Calendar
//                    weekEnd.add(Calendar.DAY_OF_YEAR, 6)
//                    val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
//                    "${formatter.format(weekStart.time)} ~ ${formatter.format(weekEnd.time)}"
//                }
//                Period.MONTH -> SimpleDateFormat("yyyy/MM", Locale.getDefault()).format(currentDate.time)
//                Period.YEAR -> SimpleDateFormat("yyyy", Locale.getDefault()).format(currentDate.time)
//            }
//        }
//}
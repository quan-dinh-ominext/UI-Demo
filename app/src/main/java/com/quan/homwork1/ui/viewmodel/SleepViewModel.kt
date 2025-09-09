package com.quan.homwork1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.BarEntry
import com.quan.homwork1.data.SleepRecord
import com.quan.homwork1.data.SleepStatistics
import com.quan.homwork1.data.TimeFilter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SleepViewModel : ViewModel() {
    private val _selectedTimeFilter = MutableStateFlow(TimeFilter.MONTH)
    val selectedTimeFilter: StateFlow<TimeFilter> = _selectedTimeFilter.asStateFlow()

    private val _currentPeriod = MutableStateFlow("2025/04")
    val currentPeriod: StateFlow<String> = _currentPeriod.asStateFlow()

    private val _chartEntries = MutableStateFlow<List<BarEntry>>(emptyList())
    val chartEntries: StateFlow<List<BarEntry>> = _chartEntries.asStateFlow()

    private val _chartLabels = MutableStateFlow<List<String>>(emptyList())
    val chartLabels: StateFlow<List<String>> = _chartLabels.asStateFlow()

    private val _sleepStatistics = MutableStateFlow(
        SleepStatistics(
            averageSleepTime = "8h 30m",
            averageBedtime = "20:00",
            averageWakeupTime = "6:00",
            sleepGoal = "8h"
        )
    )
    val sleepStatistics: StateFlow<SleepStatistics> = _sleepStatistics.asStateFlow()

    private val _sleepRecords = MutableStateFlow<List<SleepRecord>>(emptyList())
    val sleepRecords: StateFlow<List<SleepRecord>> = _sleepRecords.asStateFlow()

    private val _expandedRecord = MutableStateFlow<String?>(null)
    val expandedRecord: StateFlow<String?> = _expandedRecord.asStateFlow()

    // Current date references for navigation
    private var currentYear = LocalDate.now().year
    private var currentMonth = LocalDate.now().monthValue
    private var currentWeekStartDate = LocalDate.now()

    init {
        loadSampleData()
        updatePeriodAndData()
    }

    fun selectTimeFilter(filter: TimeFilter) {
        _selectedTimeFilter.value = filter
        // Reset to current date when switching filters
        when (filter) {
            TimeFilter.YEAR -> currentYear = LocalDate.now().year
            TimeFilter.MONTH -> {
                currentYear = LocalDate.now().year
                currentMonth = LocalDate.now().monthValue
            }
            TimeFilter.WEEK -> currentWeekStartDate = LocalDate.now()
        }
        updatePeriodAndData()
    }

    fun navigatePrevious() {
        when (_selectedTimeFilter.value) {
            TimeFilter.YEAR -> {
                currentYear--
            }
            TimeFilter.MONTH -> {
                if (currentMonth == 1) {
                    currentMonth = 12
                    currentYear--
                } else {
                    currentMonth--
                }
            }
            TimeFilter.WEEK -> {
                currentWeekStartDate = currentWeekStartDate.minusWeeks(1)
            }
        }
        updatePeriodAndData()
    }

    fun navigateNext() {
        when (_selectedTimeFilter.value) {
            TimeFilter.YEAR -> {
                currentYear++
            }
            TimeFilter.MONTH -> {
                if (currentMonth == 12) {
                    currentMonth = 1
                    currentYear++
                } else {
                    currentMonth++
                }
            }
            TimeFilter.WEEK -> {
                currentWeekStartDate = currentWeekStartDate.plusWeeks(1)
            }
        }
        updatePeriodAndData()
    }

    fun toggleRecordExpansion(date: String) {
        _expandedRecord.value = if (_expandedRecord.value == date) null else date
    }

    private fun updatePeriodAndData() {
        when (_selectedTimeFilter.value) {
            TimeFilter.WEEK -> {
                val weekFields = WeekFields.of(Locale.getDefault())
                val startOfWeek = currentWeekStartDate.with(weekFields.dayOfWeek(), 1)
                val endOfWeek = startOfWeek.plusDays(6)

                _currentPeriod.value = "${startOfWeek.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))} ~ ${endOfWeek.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))}"
                loadWeeklyData()
            }
            TimeFilter.MONTH -> {
                _currentPeriod.value = String.format("%04d/%02d", currentYear, currentMonth)
                loadMonthlyData()
            }
            TimeFilter.YEAR -> {
                _currentPeriod.value = currentYear.toString()
                loadYearlyData()
            }
        }
    }

    private fun loadSampleData() {
        loadSleepRecords()
    }

    private fun loadWeeklyData() {
        val entries = listOf(
            BarEntry(0f, 7.5f),  // Monday
            BarEntry(1f, 8.0f),  // Tuesday
            BarEntry(2f, 6.5f),  // Wednesday
            BarEntry(3f, 7.8f),  // Thursday
            BarEntry(4f, 8.2f),  // Friday
            BarEntry(5f, 9.0f),  // Saturday
            BarEntry(6f, 8.5f)   // Sunday
        )
        val labels = listOf("月", "火", "水", "木", "金", "土", "日")

        _chartEntries.value = entries
        _chartLabels.value = labels
    }

    private fun loadMonthlyData() {
        val entries = listOf(
            BarEntry(0f, 7.2f),
            BarEntry(1f, 8.1f),
            BarEntry(2f, 7.8f),
            BarEntry(3f, 8.5f)
        )
        val labels = listOf("第1週", "第2週", "第3週", "第4週")

        _chartEntries.value = entries
        _chartLabels.value = labels
    }

    private fun loadYearlyData() {
        val entries = listOf(
            BarEntry(0f, 7.5f),   // January
            BarEntry(1f, 7.8f),   // February
            BarEntry(2f, 8.0f),   // March
        )
        val labels = listOf("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月")

        _chartEntries.value = entries
        _chartLabels.value = labels
    }

    private fun loadSleepRecords() {
        _sleepRecords.value = listOf(
            SleepRecord("2025/04/20", "8h 30m", "よく眠れた", "6:00", "20:00", 8.5f),
            SleepRecord("2025/04/19", "8h 30m", "", "", "", 8.5f),
            SleepRecord("2025/04/18", "8h 30m", "", "", "", 8.5f),
            SleepRecord("2025/04/17", "8h 30m", "", "", "", 8.5f)
        )
    }
}
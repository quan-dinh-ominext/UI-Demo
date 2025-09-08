package com.quan.homwork1.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.quan.homwork1.data.BloodSugarConstants.MeasurementPeriod
import com.quan.homwork1.data.model.BloodSugarEntry
import com.quan.homwork1.ui.state.BloodSugarUiState
import java.text.SimpleDateFormat
import java.util.*

class BloodSugarViewModel : ViewModel() {

    private val _uiState = mutableStateOf(BloodSugarUiState())
    val uiState: State<BloodSugarUiState> = _uiState

    init {
        // Set default time based on current time
        setDefaultTime()
    }

    fun updateBloodSugarValue(value: String) {
        val cleanValue = value.filter { it.isDigit() }
        if (cleanValue.isEmpty() || (cleanValue.toIntOrNull()?.let { it <= 800 } == true)) {
            _uiState.value = _uiState.value.copy(
                bloodSugarValue = cleanValue,
                errorMessage = validateBloodSugar(cleanValue)
            )
        }
    }

    fun updateMeasurementTime(time: String) {
        _uiState.value = _uiState.value.copy(
            measurementTime = time,
            errorMessage = validateTime(time)
        )
    }

    fun selectPeriod(period: MeasurementPeriod) {
        _uiState.value = _uiState.value.copy(
            selectedPeriod = period,
            measurementTime = getDefaultTimeForPeriod(period)
        )
    }

    fun updateDate(date: String) {
        _uiState.value = _uiState.value.copy(selectedDate = date)
        // In a real app, you'd load entries for the new date here
        loadEntriesForDate(date)
    }

    fun addEntry() {
        val currentState = _uiState.value

        if (!currentState.isFormValid()) {
            _uiState.value = currentState.copy(
                errorMessage = "すべての項目を正しく入力してください"
            )
            return
        }

        _uiState.value = currentState.copy(isLoading = true)

        try {
            val newEntry = BloodSugarEntry(
                value = currentState.bloodSugarValue.toInt(),
                measurementTime = currentState.measurementTime,
                period = currentState.selectedPeriod,
                date = currentState.selectedDate
            )

            val updatedEntries = currentState.todayEntries.toMutableList()

            // Remove existing entry for the same period if exists
            updatedEntries.removeAll { it.period == currentState.selectedPeriod }
            updatedEntries.add(newEntry)
            updatedEntries.sortBy { it.measurementTime }

            _uiState.value = currentState.copy(
                todayEntries = updatedEntries,
                isLoading = false,
                errorMessage = null
            )

            resetForm()
        } catch (e: Exception) {
            _uiState.value = currentState.copy(
                errorMessage = "記録の追加に失敗しました",
                isLoading = false
            )
        }
    }

    fun deleteEntry(entryId: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            todayEntries = currentState.todayEntries.filter { it.id != entryId }
        )
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    private fun resetForm() {
        _uiState.value = _uiState.value.copy(
            bloodSugarValue = "",
            errorMessage = null
        )
        setDefaultTime()
    }

    private fun setDefaultTime() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)

        val (period, time) = if (hour < 12) {
            MeasurementPeriod.MORNING to "07:00"
        } else {
            MeasurementPeriod.AFTERNOON to "17:00"
        }

        _uiState.value = _uiState.value.copy(
            selectedPeriod = period,
            measurementTime = time
        )
    }

    private fun getDefaultTimeForPeriod(period: MeasurementPeriod): String {
        return when (period) {
            MeasurementPeriod.MORNING -> "07:00"
            MeasurementPeriod.AFTERNOON -> "17:00"
        }
    }

    private fun validateBloodSugar(value: String): String? {
        if (value.isEmpty()) return null
        val intValue = value.toIntOrNull()
        return when {
            intValue == null -> "有効な数値を入力してください"
            intValue < 20 -> "20以上の値を入力してください"
            intValue > 800 -> "800以下の値を入力してください"
            else -> null
        }
    }

    private fun validateTime(time: String): String? {
        if (time.isEmpty()) return null
        val timeRegex = Regex("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
        return if (!time.matches(timeRegex)) {
            "正しい時間形式で入力してください (HH:MM)"
        } else null
    }

    private fun loadEntriesForDate(date: String) {
        // In a real app, this would load from database
        // For now, keep current entries if it's today's date
        val today = SimpleDateFormat("yyyy/MM/dd", Locale.JAPANESE).format(Date())
        if (date != today) {
            _uiState.value = _uiState.value.copy(todayEntries = emptyList())
        }
    }
}
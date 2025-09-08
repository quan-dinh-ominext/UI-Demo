package com.quan.homwork1.ui.state

import com.quan.homwork1.data.BloodSugarConstants.MeasurementPeriod
import com.quan.homwork1.data.model.BloodSugarEntry
import java.text.SimpleDateFormat
import java.util.*

data class BloodSugarUiState(
    val bloodSugarValue: String = "",
    val measurementTime: String = "",
    val selectedPeriod: MeasurementPeriod = MeasurementPeriod.MORNING,
    val selectedDate: String = getCurrentDate(),
    val todayEntries: List<BloodSugarEntry> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
) {
    fun isFormValid(): Boolean {
        return bloodSugarValue.isNotEmpty() &&
                measurementTime.isNotEmpty() &&
                bloodSugarValue.toIntOrNull() != null &&
                bloodSugarValue.toInt() in 20..800 && // Valid blood sugar range
                measurementTime.matches(Regex("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$"))
    }

    companion object {
        private fun getCurrentDate(): String {
            val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.JAPANESE)
            return sdf.format(Date())
        }
    }
}
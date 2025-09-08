package com.quan.homwork1.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.quan.homwork1.data.AlcoholConstants
import com.quan.homwork1.data.model.HistoryEntry
import com.quan.homwork1.ui.state.AlcoholUiState

class AlcoholViewModel : ViewModel() {

    private val _uiState = mutableStateOf(AlcoholUiState())
    val uiState: State<AlcoholUiState> = _uiState

    fun updateAlcoholPercentage(value: String) {
        val cleanValue = value.filter { it.isDigit() || it == '.' }
        if (isValidPercentageInput(cleanValue)) {
            _uiState.value = _uiState.value.copy(
                alcoholPercentage = cleanValue,
                selectedAlcoholType = findMatchingAlcoholType(cleanValue),
                errorMessage = validatePercentage(cleanValue)
            )
        }
    }

    fun updateVolume(value: String) {
        val cleanValue = value.filter { it.isDigit() || it == '.' }
        if (isValidVolumeInput(cleanValue)) {
            _uiState.value = _uiState.value.copy(
                volume = cleanValue,
                selectedVolumeType = findMatchingVolumeType(cleanValue),
                errorMessage = validateVolume(cleanValue)
            )
        }
    }

    fun updateCount(count: Int) {
        val validCount = maxOf(0, count)
        _uiState.value = _uiState.value.copy(
            count = validCount,
            errorMessage = if (validCount <= 0) null else _uiState.value.errorMessage
        )
    }

    fun selectAlcoholType(type: String, value: String) {
        val currentState = _uiState.value
        if (currentState.selectedAlcoholType == type) {
            // Deselect
            _uiState.value = currentState.copy(
                selectedAlcoholType = "",
                alcoholPercentage = "",
                errorMessage = null
            )
        } else {
            // Select
            _uiState.value = currentState.copy(
                selectedAlcoholType = type,
                alcoholPercentage = value,
                errorMessage = null
            )
        }
    }

    fun selectVolumeType(type: String, value: String) {
        val currentState = _uiState.value
        if (currentState.selectedVolumeType == type) {
            // Deselect
            _uiState.value = currentState.copy(
                selectedVolumeType = "",
                volume = "",
                errorMessage = null
            )
        } else {
            // Select
            _uiState.value = currentState.copy(
                selectedVolumeType = type,
                volume = value,
                errorMessage = null
            )
        }
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
            val newEntry = HistoryEntry(
                volume = "${currentState.volume}ml",
                percentage = "${currentState.alcoholPercentage}%",
                count = currentState.count
            )

            _uiState.value = currentState.copy(
                historyEntries = currentState.historyEntries + newEntry,
                isLoading = false
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
            historyEntries = currentState.historyEntries.filter { it.id != entryId }
        )
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    private fun resetForm() {
        _uiState.value = _uiState.value.copy(
            alcoholPercentage = "",
            volume = "",
            count = 0,
            selectedAlcoholType = "",
            selectedVolumeType = "",
            errorMessage = null
        )
    }

    private fun isValidPercentageInput(value: String): Boolean {
        if (value.isEmpty()) return true
        val doubleValue = value.toDoubleOrNull() ?: return false
        return doubleValue >= 0 && doubleValue <= 100
    }

    private fun isValidVolumeInput(value: String): Boolean {
        if (value.isEmpty()) return true
        val doubleValue = value.toDoubleOrNull() ?: return false
        return doubleValue >= 0 && doubleValue <= 10000 // Max 10L
    }

    private fun validatePercentage(value: String): String? {
        if (value.isEmpty()) return null
        val doubleValue = value.toDoubleOrNull()
        return when {
            doubleValue == null -> "有効な数値を入力してください"
            doubleValue < 0 -> "0以上の値を入力してください"
            doubleValue > 100 -> "100以下の値を入力してください"
            else -> null
        }
    }

    private fun validateVolume(value: String): String? {
        if (value.isEmpty()) return null
        val doubleValue = value.toDoubleOrNull()
        return when {
            doubleValue == null -> "有効な数値を入力してください"
            doubleValue <= 0 -> "0より大きい値を入力してください"
            doubleValue > 10000 -> "10000ml以下の値を入力してください"
            else -> null
        }
    }

    private fun findMatchingAlcoholType(value: String): String {
        return AlcoholConstants.ALCOHOL_TYPES.find { it.second == value }?.first ?: ""
    }

    private fun findMatchingVolumeType(value: String): String {
        return AlcoholConstants.VOLUME_TYPES.find { it.second == value }?.first ?: ""
    }
}
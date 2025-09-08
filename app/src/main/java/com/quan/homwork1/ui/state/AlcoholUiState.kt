package com.quan.homwork1.ui.state

import com.quan.homwork1.data.model.HistoryEntry

data class AlcoholUiState(
    val alcoholPercentage: String = "",
    val volume: String = "",
    val count: Int = 0,
    val selectedAlcoholType: String = "",
    val selectedVolumeType: String = "",
    val historyEntries: List<HistoryEntry> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
) {
    fun isFormValid(): Boolean {
        return alcoholPercentage.isNotEmpty() &&
                volume.isNotEmpty() &&
                count > 0 &&
                alcoholPercentage.toDoubleOrNull() != null &&
                volume.toDoubleOrNull() != null &&
                alcoholPercentage.toDouble() in 0.0..100.0 &&
                volume.toDouble() > 0
    }
}
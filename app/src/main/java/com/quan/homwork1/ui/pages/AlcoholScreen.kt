package com.quan.homwork1.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quan.homwork1.ui.components.buttons.SubmitButton
import com.quan.homwork1.ui.components.common.DateNavigationBar
import com.quan.homwork1.ui.components.common.ErrorMessage
import com.quan.homwork1.ui.components.sections.*
import com.quan.homwork1.ui.viewmodel.AlcoholViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlcoholScreen(
    viewModel: AlcoholViewModel = viewModel()
) {
    val uiState by viewModel.uiState
    var selectedDate by remember { mutableStateOf(Date()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            DateNavigationBar(
                currentDate = selectedDate,
                onDateChanged = { newDate ->
                    selectedDate = newDate
                }
            )

            uiState.errorMessage?.let { error ->
                ErrorMessage(
                    message = error,
                    onDismiss = viewModel::clearError
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            AlcoholPercentageSection(
                value = uiState.alcoholPercentage,
                selectedType = uiState.selectedAlcoholType,
                onValueChange = viewModel::updateAlcoholPercentage,
                onTypeSelected = viewModel::selectAlcoholType,
                isError = uiState.errorMessage?.contains("度数") == true
            )

            Spacer(modifier = Modifier.height(20.dp))

            VolumeSection(
                value = uiState.volume,
                selectedType = uiState.selectedVolumeType,
                onValueChange = viewModel::updateVolume,
                onTypeSelected = viewModel::selectVolumeType,
                isError = uiState.errorMessage?.contains("量") == true
            )

            Spacer(modifier = Modifier.height(16.dp))

            CountSection(
                count = uiState.count,
                onCountChange = viewModel::updateCount
            )

            Spacer(modifier = Modifier.height(16.dp))

            HistorySection(
                historyEntries = uiState.historyEntries,
                onDeleteEntry = viewModel::deleteEntry
            )

            Spacer(modifier = Modifier.height(32.dp))

            SubmitButton(
                isEnabled = uiState.isFormValid(),
                isLoading = uiState.isLoading,
                onClick = viewModel::addEntry
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
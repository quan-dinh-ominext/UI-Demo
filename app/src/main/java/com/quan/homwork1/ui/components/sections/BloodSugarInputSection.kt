package com.quan.homwork1.ui.components.sections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quan.homwork1.data.BloodSugarConstants.MeasurementPeriod
import com.quan.homwork1.ui.components.common.CustomTextField
import com.quan.homwork1.ui.components.common.TimePickerField

@Composable
fun BloodSugarInputSection(
    bloodSugarValue: String,
    measurementTime: String,
    selectedPeriod: MeasurementPeriod,
    onBloodSugarValueChange: (String) -> Unit,
    onTimeChange: (String) -> Unit,
    onPeriodChange: (MeasurementPeriod) -> Unit,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Blood Sugar Input
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "血糖 (mg/dL)",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                CustomTextField(
                    value = bloodSugarValue,
                    onValueChange = onBloodSugarValueChange,
                    placeholder = "95",
                    isError = isError
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "測定時間",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TimePickerField(
                    time = measurementTime,
                    onTimeSelected = onTimeChange
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Measurement Period Selection
        Text(
            text = "測定期間",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MeasurementPeriod.values().forEach { period ->
                PeriodChip(
                    text = period.displayName,
                    isSelected = selectedPeriod == period,
                    onClick = { onPeriodChange(period) }
                )
            }
        }
    }
}

@Composable
fun PeriodChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) Color(0xFF4285F4) else Color.White
    val textColor = if (isSelected) Color.White else Color.Gray
    val borderColor = if (isSelected) Color(0xFF4285F4) else Color.Gray.copy(alpha = 0.3f)

    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                color = textColor
            )
        },
        modifier = modifier,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = backgroundColor,
            selectedContainerColor = Color(0xFF4285F4)
        ),
//        border = FilterChipDefaults.filterChipBorder(
//            borderColor = borderColor,
//            selectedBorderColor = Color(0xFF4285F4)
//        )
    )
}
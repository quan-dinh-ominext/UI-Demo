package com.quan.homwork1.ui.components.sections

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quan.homwork1.data.AlcoholConstants
import com.quan.homwork1.ui.common.SectionTitle
import com.quan.homwork1.ui.components.common.CustomTextField
import com.quan.homwork1.ui.components.common.LazyChipGrid

@Composable
fun AlcoholPercentageSection(
    value: String,
    selectedType: String,
    onValueChange: (String) -> Unit,
    onTypeSelected: (String, String) -> Unit,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SectionTitle(
            mainText = "アルコール度数",
            subText = " (%)",
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = "-",
            isError = isError
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyChipGrid(
            items = AlcoholConstants.ALCOHOL_TYPES,
            selectedItem = selectedType,
            onItemSelected = onTypeSelected
        )
    }
}
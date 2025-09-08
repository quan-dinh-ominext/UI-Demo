package com.quan.homwork1.ui.components.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LazyChipGrid(
    items: List<Pair<String, String>>,
    selectedItem: String,
    onItemSelected: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { (text, value) ->
            ChipButton(
                text = text,
                isSelected = selectedItem == text,
                onClick = { onItemSelected(text, value) }
            )
        }
    }
}
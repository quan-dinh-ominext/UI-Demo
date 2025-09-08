package com.quan.homwork1.ui.components.sections

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quan.homwork1.ui.common.NumberInputField
import com.quan.homwork1.ui.common.SectionTitle

@Composable
fun CountSection(
    count: Int,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SectionTitle(mainText = "数")

        Spacer(modifier = Modifier.height(8.dp))

        NumberInputField(
            value = count,
            onValueChange = onCountChange,
        )
    }
}
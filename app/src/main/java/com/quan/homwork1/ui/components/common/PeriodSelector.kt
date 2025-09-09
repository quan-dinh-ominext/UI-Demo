package com.quan.homwork1.ui.components.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import com.quan.homwork1.ui.theme.Dimens
import com.quan.homwork1.ui.theme.TextPrimary
import com.quan.homwork1.data.Period


@Composable
fun PeriodSelector(
    periods: List<Period>,
    selectedPeriod: Period,
    onPeriodSelected: (Period) -> Unit,
    modifier: Modifier = Modifier,
    // Tham số mới cho state hoisting
    animatedOffset: Dp,
    indicatorWidth: Dp,
    onSizeChanged: (IntSize) -> Unit
) {
    val options = periods.map { stringResource(id = it.titleResId) }
    val selectedIndex = remember(periods, selectedPeriod) { periods.indexOf(selectedPeriod) }

    CustomSegmentedControl(
        modifier = modifier,
        options = options,
        selectedOptionIndex = selectedIndex,
        onOptionSelected = { onPeriodSelected(periods[it]) },
        height = Dimens.dp36,
        cornerRadius = Dimens.dp8,
        enableColor = TextPrimary,
        // Truyền state đã hoist xuống
        animatedOffset = animatedOffset,
        indicatorWidth = indicatorWidth,
        onSizeChanged = onSizeChanged
    )
}
package com.quan.homwork1.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import com.quan.homwork1.ui.theme.BackgroundColorSwitch
import com.quan.homwork1.ui.theme.BorderColor
import com.quan.homwork1.ui.theme.ColorPrimary
import com.quan.homwork1.ui.theme.Dimens
import com.quan.homwork1.ui.theme.TextSecondary
import com.quan.homwork1.ui.theme.White
import com.quan.homwork1.ui.theme.title14sp

@Composable
fun CustomSegmentedControl(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOptionIndex: Int,
    onOptionSelected: (Int) -> Unit,
    height: Dp = Dimens.dp36,
    cornerRadius: Dp = height / 2,
    enableColor: Color = ColorPrimary,
    // Tham số mới được hoist từ parent
    animatedOffset: Dp,
    indicatorWidth: Dp,
    onSizeChanged: (IntSize) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
            .background(BackgroundColorSwitch)
            .border(Dimens.dp1, BorderColor, RoundedCornerShape(cornerRadius))
            .onSizeChanged { onSizeChanged(it) } // Báo cáo kích thước về parent
    ) {
        // Con nhộng (Indicator) sử dụng giá trị đã được animate từ parent
        if (indicatorWidth > Dimens.dp0) {
            Box(
                modifier = Modifier
                    .offset(x = animatedOffset)
                    .width(indicatorWidth)
                    .fillMaxHeight()
                    .padding(Dimens.dp4)
                    .shadow(elevation = Dimens.dp2, shape = RoundedCornerShape(cornerRadius - Dimens.dp2), clip = false)
                    .background(White, RoundedCornerShape(cornerRadius - Dimens.dp2))
            )
        }

        // Các Text cho tùy chọn
        Row(
            modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEachIndexed { index, optionText ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onOptionSelected(index) }), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = optionText,
                        color = if (selectedOptionIndex == index) enableColor else TextSecondary,
                        style = title14sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
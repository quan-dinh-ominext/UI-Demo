package com.quan.homwork1.ui.components.common

// CustomBarChart.kt
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.utils.ViewPortHandler

class CustomBarChart @JvmOverloads constructor(
    context: android.content.Context,
    attrs: android.util.AttributeSet? = null,
    defStyle: Int = 0
) : BarChart(context, attrs, defStyle) {

    init {
        renderer = CustomBarChartRenderer(this, animator, viewPortHandler)
    }
}
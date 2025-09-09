package com.quan.homwork1.ui.components.common

// CustomBarChartRenderer.kt
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.utils.ViewPortHandler
import android.graphics.*
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.buffer.BarBuffer
import android.graphics.*

class CustomBarChartRenderer(
    chart: BarChart,
    animator: ChartAnimator,
    viewPortHandler: ViewPortHandler
) : BarChartRenderer(chart, animator, viewPortHandler) {

    override fun drawData(c: Canvas) {
        // Draw vertical lines first (behind bars)
        drawVerticalLines(c)

        // Draw goal line behind bars
        drawGoalLine(c)

        // Draw thick bottom line
        drawBottomLine(c)

        // Draw bars with custom gradient
        drawBarsWithGradient(c)
    }

    private fun drawBarsWithGradient(canvas: Canvas) {
        val barData = mChart.barData ?: return

        for (i in 0 until barData.dataSetCount) {
            val dataSet = barData.getDataSetByIndex(i)
            if (!shouldDrawValues(dataSet) || dataSet.entryCount == 0) continue

            drawDataSetWithGradient(canvas, dataSet, i)
        }
    }

    private fun drawDataSetWithGradient(canvas: Canvas, dataSet: IBarDataSet, index: Int) {
        val trans = mChart.getTransformer(dataSet.axisDependency)

        mBarBorderPaint.color = dataSet.barBorderColor
        mBarBorderPaint.strokeWidth = dataSet.barBorderWidth

        val drawBorder = dataSet.barBorderWidth > 0f

        val phaseX = Math.max(0f, Math.min(1f, mAnimator.phaseX))
        val phaseY = mAnimator.phaseY

        // Create buffer for this dataset
        val buffer = mBarBuffers[index]
        buffer.setPhases(phaseX, phaseY)
        buffer.setDataSet(index)
        buffer.setInverted(mChart.isInverted(dataSet.axisDependency))
        buffer.setBarWidth(mChart.barData.barWidth)
        buffer.feed(dataSet)

        trans.pointValuesToPixel(buffer.buffer)

        // Draw each bar with individual gradient
        for (j in 0 until buffer.size() step 4) {
            if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) continue
            if (!mViewPortHandler.isInBoundsRight(buffer.buffer[j])) break

            val left = buffer.buffer[j]
            val top = buffer.buffer[j + 1]
            val right = buffer.buffer[j + 2]
            val bottom = buffer.buffer[j + 3]

            // Skip if bar has no height
            if (top == bottom) continue

            // Create gradient for this specific bar
            val gradient = LinearGradient(
                0f, bottom,  // Start at bottom (dark)
                0f, top,     // End at top (light)
                Color.parseColor("#7B1FA2"), // Dark purple at bottom
                Color.parseColor("#E1BEE7"), // Light purple at top
                Shader.TileMode.CLAMP
            )

            // Create paint with gradient
            val gradientPaint = Paint().apply {
                shader = gradient
                isAntiAlias = true
                style = Paint.Style.FILL
            }

            // Draw the bar with gradient
            canvas.drawRect(left, top, right, bottom, gradientPaint)

            // Draw border if needed
            if (drawBorder) {
                canvas.drawRect(left, top, right, bottom, mBarBorderPaint)
            }
        }
    }

    private fun drawVerticalLines(canvas: Canvas) {
        val chart = mChart as BarChart
        val data = chart.data ?: return

        val paint = Paint().apply {
            color = Color.parseColor("#E0E0E0")
            strokeWidth = 2f
            pathEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        }

        val positions = listOf(6.5f, 12.5f, 18.5f)

        positions.forEach { position ->
            val transformer = chart.getTransformer(chart.data.getDataSetByIndex(0).axisDependency)
            val x = transformer.getPixelForValues(position, 0f).x.toFloat()

            canvas.drawLine(
                x,
                mViewPortHandler.contentTop(),
                x,
                mViewPortHandler.contentBottom(),
                paint
            )
        }
    }

    private fun drawGoalLine(canvas: Canvas) {
        val chart = mChart as BarChart

        val paint = Paint().apply {
            color = Color.parseColor("#2196F3")
            strokeWidth = 4f
            pathEffect = DashPathEffect(floatArrayOf(20f, 10f), 0f)
        }

        val transformer = chart.getTransformer(chart.data.getDataSetByIndex(0).axisDependency)
        val y = transformer.getPixelForValues(0f, 8f).y.toFloat()

        canvas.drawLine(
            mViewPortHandler.contentLeft(),
            y,
            mViewPortHandler.contentRight(),
            y,
            paint
        )
    }

    private fun drawBottomLine(canvas: Canvas) {
        val chart = mChart as BarChart

        val paint = Paint().apply {
            color = Color.parseColor("#333333")
            strokeWidth = 6f
            style = Paint.Style.STROKE
            isAntiAlias = true
        }

        val transformer = chart.getTransformer(chart.data.getDataSetByIndex(0).axisDependency)
        val y = transformer.getPixelForValues(0f, 0f).y.toFloat()

        canvas.drawLine(
            mViewPortHandler.contentLeft(),
            y,
            mViewPortHandler.contentRight(),
            y,
            paint
        )
    }
}
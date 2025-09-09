//package com.quan.homwork1.ui.pages
//
//
//import androidx.compose.animation.core.Animatable
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.IntSize
//import androidx.compose.ui.unit.dp
//import com.quan.homwork1.ui.components.common.DateNavigator
//import com.quan.homwork1.ui.components.common.PeriodSelector
//import com.quan.homwork1.ui.state.HealthDataUiState
//import com.quan.homwork1.ui.theme.Dimens
//import com.quan.homwork1.ui.viewmodel.HealthDataViewModel
//import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.launch
//import java.util.Calendar
//import com.quan.homwork1.data.Period
//
//@Composable
//fun StepsContent(
//    uiState: HealthDataUiState, viewModel: HealthDataViewModel = hiltViewModel(), onClickHealthConnect: () -> Unit = {}
//) {
//    var expandedItemId by remember { mutableStateOf<Calendar?>(null) }
//    val axisMax = 15000f
//
//    // === State Hoisting cho Chart ===
//    val animatedChartValues = remember(uiState.chartData) {
//        uiState.chartData.map { Animatable(0f) }
//    }
//    LaunchedEffect(uiState.chartData) {
//        coroutineScope {
//            animatedChartValues.forEachIndexed { index, animatable ->
//                launch {
//                    animatable.animateTo(
//                        targetValue = uiState.chartData[index].value.coerceAtMost(axisMax), animationSpec = tween(800)
//                    )
//                }
//            }
//        }
//    }
//
//    // === State Hoisting cho PeriodSelector ===
//    val density = LocalDensity.current
//    var controlSize by remember { mutableStateOf(IntSize.Zero) }
//    val selectedIndex = remember(uiState.periods, uiState.selectedPeriod) {
//        uiState.periods.indexOf(uiState.selectedPeriod)
//    }
//    val indicatorWidth = remember(controlSize, uiState.periods.size) {
//        if (uiState.periods.isNotEmpty() && controlSize.width > 0) {
//            with(density) { (controlSize.width / uiState.periods.size).toDp() }
//        } else {
//            0.dp
//        }
//    }
//    val targetOffsetPx = with(density) { (indicatorWidth * selectedIndex).toPx() }
//    val animatedIndicatorOffset = remember { Animatable(targetOffsetPx) }
//
//    LaunchedEffect(targetOffsetPx) {
//        // Snap đến vị trí ban đầu khi mới được đo đạc mà không animate
//        if (animatedIndicatorOffset.value == 0f && targetOffsetPx != 0f) {
//            animatedIndicatorOffset.snapTo(targetOffsetPx)
//        } else {
//            // Animate khi người dùng chọn mục khác
//            animatedIndicatorOffset.animateTo(
//                targetValue = targetOffsetPx, animationSpec = tween(durationMillis = 300)
//            )
//        }
//    }
//    // === Kết thúc State Hoisting ===
//
//    LaunchedEffect(uiState.stepDetails) {
//        if (uiState.selectedPeriod != Period.YEAR && uiState.stepDetails.isNotEmpty()) {
//            expandedItemId = uiState.stepDetails.first().date
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            contentPadding = PaddingValues(bottom = Dimens.dp16)
//        ) {
//            item {
//                PeriodSelector(
//                    periods = uiState.periods,
//                    selectedPeriod = uiState.selectedPeriod,
//                    onPeriodSelected = { viewModel.onPeriodSelected(it) },
//                    modifier = Modifier.padding(Dimens.dp16),
//                    // Truyền state đã hoist xuống
//                    animatedOffset = with(density) { animatedIndicatorOffset.value.toDp() },
//                    indicatorWidth = indicatorWidth,
//                    onSizeChanged = { controlSize = it })
//            }
//            if (uiState.selectedPeriod != Period.DAY) {
//                item {
//                    DateNavigator(
//                        dateRange = uiState.formattedDateRange,
//                        onPrevious = { viewModel.onNavigateDate(false) },
//                        onNext = { viewModel.onNavigateDate(true) })
//                }
//            }
//
//            when (uiState.selectedPeriod) {
//                Period.DAY -> {
//                    items(uiState.stepDetails, key = { it.date.timeInMillis }) { detail ->
//                        val isExpanded = expandedItemId?.timeInMillis == detail.date.timeInMillis
//                        if (isExpanded) {
//                            ExpandedStepDetailItem(
//                                detail = detail,
//                                onToggle = { expandedItemId = null },
//                                modifier = Modifier
//                                    .padding(horizontal = Dimens.dp16, vertical = Dimens.dp4)
//                                    .animateItem()
//                            )
//                        } else {
//                            CollapsedStepDetailItem(
//                                detail = detail,
//                                onToggle = { expandedItemId = detail.date },
//                                modifier = Modifier
//                                    .padding(horizontal = Dimens.dp16, vertical = Dimens.dp4)
//                                    .animateItem()
//                            )
//                        }
//                    }
//                }
//
//                Period.WEEK, Period.MONTH -> {
//                    item {
//                        SummarySection(
//                            period = uiState.selectedPeriod,
//                            averageSteps = uiState.averageSteps,
//                            totalSteps = uiState.totalSteps,
//                            modifier = Modifier.padding(bottom = Dimens.dp16)
//                        )
//                    }
//                    item {
//                        AnimatedBarChart(
//                            data = uiState.chartData,
//                            animatedValues = animatedChartValues,
//                            goal = uiState.stepGoal,
//                            axisMax = axisMax,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(Dimens.dp200)
//                                .padding(horizontal = Dimens.dp16),
//                            gradientStartColor = StepGradientStartColor,
//                            gradientEndColor = StepGradientEndColor,
//                            healthType = TopScreenData.HealthType.STEP_TYPE
//                        )
//                    }
//                    item {
//                        DetailedStatsStep(
//                            averageCalories = uiState.averageCalories,
//                            averageDistanceMeters = uiState.averageDistanceMeters,
//                            averageWalkingTimeMinutes = uiState.averageWalkingTimeMinutes
//                        )
//                    }
//                    items(uiState.stepDetails, key = { it.date.timeInMillis }) { detail ->
//                        val isExpanded = expandedItemId?.timeInMillis == detail.date.timeInMillis
//                        if (isExpanded) {
//                            ExpandedStepDetailItem(
//                                detail = detail,
//                                onToggle = { expandedItemId = null },
//                                modifier = Modifier
//                                    .padding(horizontal = Dimens.dp16, vertical = Dimens.dp4)
//                                    .animateItem()
//                            )
//                        } else {
//                            CollapsedStepDetailItem(
//                                detail = detail,
//                                onToggle = { expandedItemId = detail.date },
//                                modifier = Modifier
//                                    .padding(horizontal = Dimens.dp16, vertical = Dimens.dp4)
//                                    .animateItem()
//                            )
//                        }
//                    }
//                }
//
//                Period.YEAR -> {
//                    item {
//                        SummarySection(
//                            period = uiState.selectedPeriod,
//                            averageSteps = uiState.averageSteps,
//                            totalSteps = uiState.totalSteps,
//                            modifier = Modifier.padding(bottom = Dimens.dp16)
//                        )
//                    }
//                    item {
//                        AnimatedBarChart(
//                            data = uiState.chartData,
//                            animatedValues = animatedChartValues,
//                            goal = uiState.stepGoal,
//                            axisMax = axisMax,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(Dimens.dp200)
//                                .padding(horizontal = Dimens.dp16),
//                            gradientStartColor = StepGradientStartColor,
//                            gradientEndColor = StepGradientEndColor,
//                            healthType = TopScreenData.HealthType.STEP_TYPE
//                        )
//                    }
//                    item {
//                        DetailedStatsStep(
//                            averageCalories = uiState.averageCalories,
//                            averageDistanceMeters = uiState.averageDistanceMeters,
//                            averageWalkingTimeMinutes = uiState.averageWalkingTimeMinutes
//                        )
//                    }
//                }
//            }
//
//            item {
//                HealthConnectBanner(modifier = Modifier.padding(Dimens.dp16), onClickHealthConnect = {
//                    onClickHealthConnect()
//                })
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//private fun StepsContent_Preview() {
//    StepsContent(
//        uiState = HealthDataUiState(
//            periods = listOf(Period.DAY, Period.WEEK, Period.MONTH, Period.YEAR),
//            selectedPeriod = Period.YEAR,
//            stepDetails = emptyList(),
//            chartData = emptyList(),
//            averageSteps = 0,
//            totalSteps = 0,
//            averageCalories = 0,
//            averageDistanceMeters = 0,
//            averageWalkingTimeMinutes = 0,
//            stepGoal = 10000
//        ),
//    )
//}
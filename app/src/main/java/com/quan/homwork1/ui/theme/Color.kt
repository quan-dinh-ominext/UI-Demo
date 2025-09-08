package com.quan.homwork1.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

val ColorPrimary = Color(0xFF4E8EFE)
val ColorPrimaryDark = Color(0xFF3A6FCB)
val ColorAccent = Color(0xFFFF5B5B)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

//background secondary #F2F3F5
val BackgroundSecondary = Color(0xFFF2F3F5)

val BackgroundDefault = White
val BackgroundWhite = Color(0xFFFFFFFF)
val ScrimColor = Color(0x80000000)

val TextPrimary = Color(0xFF384252)

//Text primary body #384252
val TextPrimaryBody = TextPrimary

val IconTintPrimary = TextPrimary
val TextSecondary = Color(0xFF5B6B86)
val IconTintSecondary = TextSecondary
val TextSubtle = Color(0xFFAAB1BA)
val TextBlue = Color(0xFF4E8EFE)
val TextSuccess = Color(0xFF4BC285)
val TextWarning = Color(0xFFFFA500)
val TextDanger = Color(0xFFFF5B5B)
val ColorDanger = TextDanger

val ColorSelected = Color(0xFF4E8EFE)
val ColorUnselected = Color(0xFF8292AA)
val NavBackground = Color(0xFFFFFFFF)

val ProgressGood = Color(0xFF4BC285)
val ProgressMid = Color(0xFFFFA500)
val ProgressBad = Color(0xFFFF5B5B)
val ProgressNeutral = Color(0xFFD1D5DB)

val NotificationTopViewColor = Color(0xFFF4F8FF)

val BorderColor = Color(0xFFE0E4EA)

val BottomNavItemSelectedColor = Color(0xFF4A80F0)
val BottomNavUnselectedColor = Color(0xFF757575)
val BottomNavIndicatorColor = Color(0xFFE8EAF6)

//achievement_banner_color #E3E5E8
val AchievementBannerColor = Color(0xFFE3E5E8)

//action_plan_color #F95691
val ActionPlanColor = Color(0xFFF95691)

//header card color #EEEFF9
val HeaderCardColor = Color(0xFFEEEFF9)

//color mod inactive feedback card #C4CBD4
val ColorModInactiveFeedbackCard = Color(0xFFC4CBD4)

//GradientStartColor #9AF9DC
val StepGradientStartColor = Color(0xFF9AF9DC)
val StepGradientEndColor = Color(0xFF27D79C)

val CaloriesGradientStartColor = Color(0xFFFCC89D)
val CaloriesGradientEndColor = Color(0xFFFF8E5E)

val FastFoodGradientStartColor = Color(0xFFFCCA9F)
val FastFoodGradientEndColor = CaloriesGradientEndColor

//TrackColorRing #ECF2FB
val TrackColorDefault = Color(0xFFECF2FB)

//Background color switch #F7F7F8
val BackgroundColorSwitch = Color(0xFFF7F7F8)

//CenterValueTextColor #2E3138
val CenterValueTextColorDefault = Color(0xFF2E3138)
val CenterUnitTextColorDefault = CenterValueTextColorDefault
//SleepProgressBarChartColor #8979FF
val SleepProgressBarChartColor = Color(0xFF8979FF)
val RangeTooltipBackgroundColor = Color(0xFFFCEDEB)
val RangeProgressColor = Color(0xFFFF7979)
val TooltipTextColor = RangeProgressColor
val AlcoholSegmentedOneColor = Color(0xFF53C2DE)
val AlcoholSegmentedTwoColor = Color(0xFF5F6FD2)
val AlcoholSegmentedThreeColor = Color(0xFF9C6CC9)
val AlcoholSegmentedFourColor = Color(0xFFF179B1)
val AlcoholSegmentedFiveColor = Color(0xFFFF7979)
val AlcoholSegmentedSixColor = Color(0xFFDE324A)

//Color title sigout #FA5C5E
val ColorTitleSigout = Color(0xFFFA5C5E)

//Color background cancel sigout #F1F3F6
val ColorBackgroundGray = Color(0xFFF1F3F6)

//Divider color #C8CED9
val DividerColor = Color(0xFFC8CED9)
val PlaceholderColor = DividerColor

//Color disable #A8B3C4
val ColorDisable = Color(0xFFA8B3C4)

val WeightColorOne = Color(0xFF5997FF)
val WeightColorTwo = Color(0xFFFFC32D)
val WeightColorThree = StepGradientEndColor
val WeightColorFour = Color(0xFFFF7979)

val BloodLowColor = WeightColorOne
val BloodNormalColor = StepGradientEndColor
val BloodSlightlyHighColor = WeightColorTwo
val BloodHighColor = WeightColorFour
val CaloriesColor = WeightColorFour

val NotificationTitleColor = Color(0xFF0042B4)

val NotificationDateColor = Color(0xFF565C67)

val NotificationUnreadColor = Color(0xFFFB8284)

//Notification gray color #878E9B
val NotificationGrayColor = Color(0xFF878E9B)

val PlusColorRecordHealth = NotificationGrayColor

val GridChartColor = Color(0xFFE5EBEF)

val BlueLineColor = Color(0xFF42A5F5)
val PinkLineColor = Color(0xFFFF7EB9)
val BlueLineColorDashed = Color(0x8042A5F5)
val PinkLineColorDashed = Color(0x80FF7EB9)
val BloodSugarColor = Color(0xFFFF7979)

val AxisColor = Color(0xFFB7C5CE)
val LabelColor = ColorUnselected
val LegendTextColor = Color(0xFF5B6B86)
val BlueBaseLineColor = Color(0xFF5794FF)
val BackgroundButtonEdit = Color(0xFFE7F0FF)

val SummaryStepColor = Color(0xFF12C78A)

//Abdomen point chart color #00C8D7
val AbdomenPointChartColor = Color(0xFF00C8D7)

//Sleep chart start color #C9C2FF
val SleepChartStartColor = Color(0xFFC9C2FF)
//Sleep chart end color #8979FF
val SleepChartEndColor = Color(0xFF8979FF)

val LightColorSchemeCustom = lightColorScheme(
    primary = ColorPrimary,
    onPrimary = White,
    primaryContainer = ColorPrimaryDark,
    onPrimaryContainer = White,
    secondary = ColorAccent,
    onSecondary = White,
    secondaryContainer = Color(0xFFFFCDD2),
    onSecondaryContainer = TextPrimary,
    tertiary = TextBlue,
    onTertiary = White,
    tertiaryContainer = Color(0xFFE3F2FD),
    onTertiaryContainer = TextPrimary,
    error = TextDanger,
    onError = White,
    errorContainer = Color(0xFFFFEBEE),
    onErrorContainer = TextPrimary,
    background = BackgroundDefault,
    onBackground = TextPrimary,
    surface = BackgroundWhite,
    onSurface = TextPrimary,
    surfaceVariant = BackgroundDefault,
    onSurfaceVariant = TextSecondary,
    outline = TextSubtle,
    inverseOnSurface = BackgroundWhite,
    inverseSurface = TextPrimary,
    inversePrimary = ColorPrimaryDark,
    surfaceTint = ColorPrimary,
    outlineVariant = TextSubtle,
    scrim = ScrimColor
)

val DarkColorSchemeCustom = darkColorScheme(
    primary = ColorPrimary,
    onPrimary = White,
    primaryContainer = ColorPrimaryDark,
    onPrimaryContainer = BackgroundDefault,
    secondary = ColorAccent,
    onSecondary = White,
    secondaryContainer = Color(0xFFB71C1C),
    onSecondaryContainer = BackgroundDefault,
    tertiary = TextBlue,
    onTertiary = White,
    tertiaryContainer = ColorPrimaryDark,
    onTertiaryContainer = BackgroundDefault,
    error = TextDanger,
    onError = White,
    errorContainer = Color(0xFFD32F2F),
    onErrorContainer = BackgroundDefault,
    background = Color(0xFF121212),
    onBackground = BackgroundDefault,
    surface = Color(0xFF1E1E1E),
    onSurface = BackgroundDefault,
    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = TextSubtle,
    outline = TextSecondary,
    inverseOnSurface = Color(0xFF121212),
    inverseSurface = BackgroundDefault,
    inversePrimary = ColorPrimaryDark,
    surfaceTint = ColorPrimary,
    outlineVariant = TextSecondary,
    scrim = ScrimColor
)
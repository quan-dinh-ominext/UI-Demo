package com.quan.homwork1.data

object BloodSugarConstants {
    // Blood sugar range categories
    data class BloodSugarRange(
        val title: String,
        val subtitle: String,
        val minValue: Int,
        val maxValue: Int,
        val category: BloodSugarCategory
    )

    enum class BloodSugarCategory {
        HYPOGLYCEMIA, // 低血糖
        NORMAL,       // 正常型
        PREDIABETES,  // 境界型
        DIABETES      // 糖尿病型
    }

    val BLOOD_SUGAR_RANGES = listOf(
        BloodSugarRange(
            title = "糖尿病型",
            subtitle = "ーあぶないー",
            minValue = 126,
            maxValue = 200,
            category = BloodSugarCategory.DIABETES
        ),
        BloodSugarRange(
            title = "正常型",
            subtitle = "ーあんしんー",
            minValue = 110,
            maxValue = 140,
            category = BloodSugarCategory.NORMAL
        )
    )

    enum class MeasurementPeriod(val displayName: String, val value: String) {
        MORNING("午前", "morning"),
        AFTERNOON("午後", "afternoon")
    }

    enum class TimePeriod(val displayName: String, val icon: String) {
        MORNING("午前", "🌅"),
        AFTERNOON("午後", "🌆")
    }

    enum class TabType(val displayName: String) {
        ABDOMEN("腹囲"),
        SLEEP("睡眠"),
        BLOOD_PRESSURE("血圧"),
        BLOOD_SUGAR("血糖"),
        ALCOHOL("飲酒"),
        EXERCISE("运動")
    }
}
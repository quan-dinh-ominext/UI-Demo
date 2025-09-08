package com.quan.homwork1.data.model

import com.quan.homwork1.data.BloodSugarConstants.BloodSugarCategory
import com.quan.homwork1.data.BloodSugarConstants.MeasurementPeriod

data class BloodSugarEntry(
    val id: String = java.util.UUID.randomUUID().toString(),
    val value: Int,
    val measurementTime: String, // "07:00", "17:00"
    val period: MeasurementPeriod,
    val date: String, // "2025/04/13"
    val timestamp: Long = System.currentTimeMillis()
) {
    fun getCategory(): BloodSugarCategory {
        return when {
            value < 70 -> BloodSugarCategory.HYPOGLYCEMIA
            value < 110 -> BloodSugarCategory.NORMAL
            value < 126 -> BloodSugarCategory.PREDIABETES
            else -> BloodSugarCategory.DIABETES
        }
    }

    fun getDisplayText(): String = "${value} mg/dL"
}

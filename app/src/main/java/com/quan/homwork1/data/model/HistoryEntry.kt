package com.quan.homwork1.data.model

data class HistoryEntry(
    val id: String = java.util.UUID.randomUUID().toString(),
    val volume: String,
    val percentage: String,
    val count: Int,
    val timestamp: Long = System.currentTimeMillis()
)
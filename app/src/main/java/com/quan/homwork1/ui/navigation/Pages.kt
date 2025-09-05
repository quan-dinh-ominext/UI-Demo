package com.quan.homwork1.ui.navigation

import kotlinx.serialization.Serializable

sealed class Pages {
    @Serializable
    object LoginScreen

    @Serializable
    data class SettingsScreen(val email: String)
}
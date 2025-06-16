package com.hakankaraotcu.focusquest.feature_quest.presentation.util

sealed class Screen(val route: String) {
    object QuestsScreen : Screen("quests_screen")
    object ProfileScreen : Screen("profile_screen")
}
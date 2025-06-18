package com.hakankaraotcu.focusquest.core.navigation

sealed class Screen(val route: String) {
    object QuestsScreen : Screen("quests_screen")
    object ProfileScreen : Screen("profile_screen")
}
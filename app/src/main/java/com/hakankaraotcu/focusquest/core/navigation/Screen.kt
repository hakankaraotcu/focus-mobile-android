package com.hakankaraotcu.focusquest.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val name: String, val route: String, val icon: ImageVector) {
    object HomeScreen: Screen("Home", "home_screen", Icons.Default.Home)
    object AllQuestsScreen : Screen("All Quests", "allquests_screen", Icons.Default.DateRange)
    object MyQuestsScreen: Screen("My Quests", "myquests_screen", Icons.Default.FavoriteBorder)
    object ProfileScreen : Screen("Profile", "profile_screen", Icons.Default.AccountCircle)

    companion object{
        val bottomNavItems = listOf(HomeScreen, AllQuestsScreen, MyQuestsScreen, ProfileScreen)
    }
}
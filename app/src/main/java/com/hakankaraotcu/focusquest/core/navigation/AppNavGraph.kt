package com.hakankaraotcu.focusquest.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hakankaraotcu.focusquest.feature_profile.presentation.profile.ProfileScreen
import com.hakankaraotcu.focusquest.feature_quest.presentation.quests.QuestsScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.QuestsScreen.route
    ) {
        composable(route = Screen.QuestsScreen.route) {
            QuestsScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
    }
}
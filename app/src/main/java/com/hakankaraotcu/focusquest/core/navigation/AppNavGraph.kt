package com.hakankaraotcu.focusquest.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hakankaraotcu.focusquest.feature_profile.presentation.profile.ProfileScreen
import com.hakankaraotcu.focusquest.HomeScreen
import com.hakankaraotcu.focusquest.MyQuestsScreen
import com.hakankaraotcu.focusquest.feature_quest.presentation.quests.QuestsScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.QuestsScreen.route,
        modifier = modifier
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.QuestsScreen.route) {
            QuestsScreen()
        }
        composable(route = Screen.MyQuestsScreen.route) {
            MyQuestsScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
    }
}
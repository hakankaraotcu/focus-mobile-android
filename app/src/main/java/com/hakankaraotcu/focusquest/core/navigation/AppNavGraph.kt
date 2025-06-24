package com.hakankaraotcu.focusquest.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hakankaraotcu.focusquest.feature_profile.presentation.profile.ProfileScreen
import com.hakankaraotcu.focusquest.feature_quest.presentation.allquests.AllQuestsScreen
import com.hakankaraotcu.focusquest.feature_quest.presentation.home.HomeScreen
import com.hakankaraotcu.focusquest.feature_quest.presentation.myquests.MyQuestsScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = modifier
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = Screen.AllQuestsScreen.route) {
            AllQuestsScreen()
        }
        composable(route = Screen.MyQuestsScreen.route) {
            MyQuestsScreen()
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
    }
}
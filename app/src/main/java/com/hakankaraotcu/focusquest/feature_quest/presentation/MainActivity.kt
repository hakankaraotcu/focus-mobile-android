package com.hakankaraotcu.focusquest.feature_quest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hakankaraotcu.focusquest.feature_quest.presentation.quests.QuestsScreen
import com.hakankaraotcu.focusquest.feature_quest.presentation.util.Screen
import com.hakankaraotcu.focusquest.presentation.dashboard.DashboardScreen
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = false
        setContent {
            FocusQuestTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.QuestsScreen.route
                    ) {
                        composable(route = Screen.QuestsScreen.route) {
                            QuestsScreen(navController = navController)
                        }
                        composable(route = Screen.ProfileScreen.route) {
                            //ProfileScreen(navController = navController)
                        }
                    }
                }
                //DashboardScreen()
            }
        }
    }
}
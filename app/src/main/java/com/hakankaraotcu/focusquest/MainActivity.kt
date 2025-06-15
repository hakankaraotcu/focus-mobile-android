package com.hakankaraotcu.focusquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.hakankaraotcu.focusquest.presentation.dashboard.DashboardScreen
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = false
        setContent {
            FocusQuestTheme {
                DashboardScreen()
            }
        }
    }
}
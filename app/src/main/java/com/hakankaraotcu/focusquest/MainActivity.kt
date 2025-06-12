package com.hakankaraotcu.focusquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hakankaraotcu.focusquest.model.Quest
import com.hakankaraotcu.focusquest.presentation.dashboard.DashboardScreen
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

class MainActivity : ComponentActivity() {
    private val questList = listOf(
        Quest(
            "Meditasyon yap",
            1,
            5,
            false
        ),
        Quest(
            "Spor yap",
            2,
            10,
            false
        ),
        Quest(
            "Bir şey öğren",
            3,
            15,
            false
        ),
        Quest(
            "Kitap oku",
            4,
            20,
            false
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocusQuestTheme {
                DashboardScreen()
                /*
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar()
                    }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Column {
                            Text(
                                text = "Uncompleted Quests",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            QuestList(questList)
                        }
                    }
                }

                 */
            }
        }
    }
}
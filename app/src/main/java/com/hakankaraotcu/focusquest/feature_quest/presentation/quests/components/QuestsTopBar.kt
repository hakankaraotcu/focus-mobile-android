package com.hakankaraotcu.focusquest.feature_quest.presentation.quests.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hakankaraotcu.focusquest.core.presentation.components.LevelProgressBar
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestsTopBar(level: Int, xp: Int, xpMax: Int) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                LevelProgressBar(level = level, xp = xp, xpForNextLevel = xpMax)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun QuestsTopBarPreview() {
    FocusQuestTheme {
        QuestsTopBar(1, 0, 10)
    }
}
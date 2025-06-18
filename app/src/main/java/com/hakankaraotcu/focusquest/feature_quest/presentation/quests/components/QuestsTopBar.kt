package com.hakankaraotcu.focusquest.feature_quest.presentation.quests.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakankaraotcu.focusquest.core.presentation.components.LevelProgressBar
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestsTopBar(level: Int, xp: Int, xpMax: Int, onClick: () -> Unit) {
    TopAppBar(
        title = {
            Column {
                LevelProgressBar(level = level, xp = xp, xpForNextLevel = xpMax)
            }
        },
        actions = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterVertically),
                    tint = Color.White,
                    contentDescription = null
                )
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
        QuestsTopBar(1, 0, 10) {}
    }
}
package com.hakankaraotcu.focusquest.feature_quest.presentation.allquests

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hakankaraotcu.focusquest.core.presentation.components.DefaultButton
import com.hakankaraotcu.focusquest.feature_quest.presentation.components.QuestItem

@Composable
fun AllQuestsScreen(
    allQuestsViewModel: AllQuestsViewModel = hiltViewModel()
) {
    val uiState by allQuestsViewModel.uiState.collectAsState()
    var lockedQuestHeight by remember { mutableStateOf(0) }

    Column {
        Spacer(Modifier.height(16.dp))
        Text(
            text = "ALL QUESTS",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.availableQuests, key = { it.id!! }) { quest ->
                QuestItem(
                    quest = quest,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    trailingContent = {
                        if (quest.isTaken) {
                            OutlinedButton(onClick = {
                                allQuestsViewModel.onEvent(AllQuestsEvent.Drop(quest))
                            }) {
                                Text(
                                    text = "Drop",
                                    color = Color.Black
                                )
                            }
                        } else {
                            DefaultButton(
                                text = "Take"
                            ) {
                                allQuestsViewModel.onEvent(AllQuestsEvent.Take(quest))
                            }
                        }

                    }
                )
            }
            if (uiState.lockedQuests.isNotEmpty()) {
                item {
                    Box(modifier = Modifier.fillMaxHeight()) {
                        QuestItem(
                            quest = uiState.lockedQuests.first(),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .graphicsLayer {
                                    alpha = 0.5f
                                }
                                .onGloballyPositioned {
                                    lockedQuestHeight = it.size.height
                                },
                            trailingContent = {}
                        )
                        Column {
                            Spacer(Modifier.height(with(LocalDensity.current) {
                                (lockedQuestHeight * 0.5f).toInt().toDp()
                            }))
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .background(
                                        color = Color.DarkGray,
                                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                                    )
                                    .border(
                                        1.dp,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(
                                            topStart = 8.dp,
                                            topEnd = 8.dp,
                                            bottomStart = 0.dp,
                                            bottomEnd = 0.dp
                                        )
                                    )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(48.dp),
                                    text = "Level up for more quests!",
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}
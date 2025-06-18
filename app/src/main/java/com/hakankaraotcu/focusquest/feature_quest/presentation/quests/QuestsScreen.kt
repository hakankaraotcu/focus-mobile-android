package com.hakankaraotcu.focusquest.feature_quest.presentation.quests

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hakankaraotcu.focusquest.R
import com.hakankaraotcu.focusquest.core.navigation.Screen
import com.hakankaraotcu.focusquest.feature_profile.presentation.profile.ProfileViewModel
import com.hakankaraotcu.focusquest.feature_quest.presentation.quests.components.EmptyStateSection
import com.hakankaraotcu.focusquest.feature_quest.presentation.quests.components.QuestItem
import com.hakankaraotcu.focusquest.feature_quest.presentation.quests.components.QuestsTopBar
import com.hakankaraotcu.focusquest.core.presentation.components.LevelUpDialog
import kotlinx.coroutines.launch

@Composable
fun QuestsScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    questViewModel: QuestsViewModel = hiltViewModel()
) {
    val uiState by profileViewModel.uiState
    val profile = uiState.profile
    val xp = uiState.xp
    val xpMax = uiState.xpMax
    val level = uiState.level
    val isLevelUpDialogOpen = uiState.isLevelUpDialogOpen
    val previousLevel = uiState.previousLevel

    val questsState = questViewModel.questsState.value

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Scaffold(
            topBar = {
                Column {
                    if (profile != null) {
                        QuestsTopBar(
                            level = level,
                            xp = xp,
                            xpMax = xpMax,
                        ) {
                            navController.navigate(Screen.ProfileScreen.route)
                        }
                    }
                }
            },
            contentWindowInsets = WindowInsets.safeContent,
            containerColor = Color.Transparent
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Text(
                            text = "UNCOMPLETED QUESTS",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }
                    if (questsState.uncompletedQuests.isEmpty()) {
                        item {
                            EmptyStateSection(
                                imageRes = R.drawable.uncomplete,
                                message = "Congratulations, you have finished all the quests."
                            )
                        }
                    }
                    items(questsState.uncompletedQuests, key = { it.id!! }) { quest ->
                        val scope = rememberCoroutineScope()
                        QuestItem(
                            quest = quest,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            onComplete = {
                                scope.launch {
                                    questViewModel.onEvent(QuestsEvent.Complete(quest))
                                    profileViewModel.handleQuestCompletion(quest.xpReward)
                                }
                            }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "COMPLETED QUESTS",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }
                    if (questsState.completedQuests.isEmpty()) {
                        item {
                            EmptyStateSection(
                                imageRes = R.drawable.complete,
                                message = "Did you really try today?\n" +
                                        "I have a feeling you've gotten a little lazy..."
                            )
                        }
                    }
                    items(questsState.completedQuests, key = { it.id!! }) { quest ->
                        QuestItem(
                            quest = quest,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            onComplete = {
                                // Not necessary
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }

    if (isLevelUpDialogOpen && profile != null) {
        LevelUpDialog(
            previousLevel = previousLevel,
            level = level,
            coinsEarned = xp,
            xpEarned = xp
        ) {
            profileViewModel.onLevelUpConfirmed()
        }
    }
}
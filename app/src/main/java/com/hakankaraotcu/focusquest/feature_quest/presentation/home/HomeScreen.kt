package com.hakankaraotcu.focusquest.feature_quest.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hakankaraotcu.focusquest.R
import com.hakankaraotcu.focusquest.core.presentation.components.DefaultButton
import com.hakankaraotcu.focusquest.core.presentation.components.LevelProgressBar
import com.hakankaraotcu.focusquest.core.presentation.components.LevelUpDialog
import com.hakankaraotcu.focusquest.feature_quest.presentation.components.QuestItem
import com.hakankaraotcu.focusquest.feature_profile.presentation.profile.ProfileViewModel
import com.hakankaraotcu.focusquest.feature_quest.presentation.components.EmptyStateSection
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    homeviewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by profileViewModel.uiState.collectAsState()
    val questsState by homeviewModel.homeState.collectAsState()

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        if (uiState.profile != null) {
            LevelProgressBar(
                level = uiState.level,
                xp = uiState.xp,
                xpForNextLevel = uiState.xpMax
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "UNCOMPLETED QUESTS",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 4.dp)
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
                    trailingContent = {
                        DefaultButton(
                            text = "Complete"
                        ) {
                            scope.launch {
                                homeviewModel.onEvent(HomeEvent.Complete(quest))
                                profileViewModel.handleQuestCompletion(quest.xpReward)
                            }
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "COMPLETED QUESTS",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 4.dp)
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
                    trailingContent = {
                        Image(
                            modifier = Modifier.size(48.dp),
                            contentDescription = "Check",
                            painter = painterResource(R.drawable.check)
                        )
                    }
                )
            }
            item {
                Spacer(Modifier.height(8.dp))
            }
        }
    }
    if (uiState.isLevelUpDialogOpen && uiState.profile != null) {
        LevelUpDialog(
            previousLevel = profileViewModel.previousLevel,
            level = uiState.level,
            coinsEarned = uiState.xp,
            xpEarned = uiState.xp
        ) {
            profileViewModel.onLevelUpConfirmed()
        }
    }
}
package com.hakankaraotcu.focusquest.feature_quest.presentation.quests

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hakankaraotcu.focusquest.R
import com.hakankaraotcu.focusquest.feature_quest.presentation.quests.components.QuestItem
import com.hakankaraotcu.focusquest.feature_quest.presentation.util.Screen
import com.hakankaraotcu.focusquest.presentation.components.DashboardTopBar
import com.hakankaraotcu.focusquest.presentation.components.LevelProgressBar

@Composable
fun QuestsScreen(
    navController: NavController,
    viewModel: QuestsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

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
                    DashboardTopBar(
                        level = 1,
                        exp = 0,
                        maxExp = 10,
                    )
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
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                LevelProgressBar(level = 1, exp = 0, expForNextLevel = 10)
//                IconButton(onClick = {
//                    //navController.navigate(Screen.ProfileScreen.route)
//                }) {
//                    Icon(
//                        modifier = Modifier.size(32.dp),
//                        imageVector = Icons.Default.AccountCircle,
//                        tint = Color.White,
//                        contentDescription = "Profile"
//                    )
//                }
//            }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Text(
                            text = "UNCOMPLETED QUESTS",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                    if (state.uncompletedQuests.isEmpty()) {
                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier.size(120.dp),
                                    painter = painterResource(R.drawable.uncomplete),
                                    contentDescription = "Uncomplete Image"
                                )
                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = "Congratulations, you have finished all the quests.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                    items(state.uncompletedQuests) { quest ->
                        QuestItem(
                            quest = quest,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            onComplete = {
                                viewModel.onEvent(QuestsEvent.Complete(quest))
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
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
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                    if (state.completedQuests.isEmpty()) {
                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier.size(120.dp),
                                    painter = painterResource(R.drawable.complete),
                                    contentDescription = "Complete Image"
                                )
                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = "Did you really try today?\n" +
                                            "I have a feeling you've gotten a little lazy...",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                    items(state.completedQuests) { quest ->
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
}
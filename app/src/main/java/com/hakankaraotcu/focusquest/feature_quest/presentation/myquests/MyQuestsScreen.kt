package com.hakankaraotcu.focusquest.feature_quest.presentation.myquests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hakankaraotcu.focusquest.core.presentation.components.DefaultButton
import com.hakankaraotcu.focusquest.feature_quest.presentation.components.QuestItem

@Composable
fun MyQuestsScreen(
    myQuestsViewModel: MyQuestsViewModel = hiltViewModel()
) {
    val uiState by myQuestsViewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = "MY QUESTS",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.takenQuests, key = { it.id!! }) { quest ->
                QuestItem(
                    quest = quest,
                    trailingContent = {
                        OutlinedButton(onClick = {
                            myQuestsViewModel.onEvent(MyQuestsEvent.Drop(quest))
                        }) {
                            Text(
                                text = "Drop",
                                color = Color.Black
                            )
                        }
                    }
                )
            }
        }

        Spacer(Modifier.height(8.dp))
    }
}
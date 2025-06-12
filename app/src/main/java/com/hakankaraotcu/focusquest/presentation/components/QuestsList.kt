package com.hakankaraotcu.focusquest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakankaraotcu.focusquest.R
import com.hakankaraotcu.focusquest.model.Quest
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

fun LazyListScope.questsList(
    sectionTitle: String,
    emptyQuestText: String,
    quests: List<Quest>
) {
    item {
        Text(
            text = sectionTitle,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
    if (quests.isEmpty()) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(R.drawable.uncomplete),
                    contentDescription = emptyQuestText
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = emptyQuestText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
    items(quests) { quest ->
        QuestCard(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            quest = quest
        )
    }
    /*
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(questList) {
            QuestRow(quest = it)
        }
    }

     */
}

@Composable
fun QuestCard(modifier: Modifier = Modifier, quest: Quest) {
    ElevatedCard(
        modifier = modifier,
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = quest.name,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (quest.isComplete) {
                        TextDecoration.LineThrough
                    } else TextDecoration.None
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = "Energy: ${quest.energy}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Exp: ${quest.exp}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            if (!quest.isComplete) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    onClick = {
                        quest.isComplete = true
                    }) {
                    Text(text = "Complete")
                }
            } else {
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(R.drawable.check),
                    contentDescription = "check"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestListPreview() {
    FocusQuestTheme {
//        QuestList(
//            questList = listOf(
//                Quest(
//                    "Meditasyon yap",
//                    1,
//                    5,
//                    false
//                ),
//                Quest(
//                    "Spor yap",
//                    2,
//                    10,
//                    false
//                ),
//                Quest(
//                    "Bir şey öğren",
//                    3,
//                    15,
//                    false
//                ),
//                Quest(
//                    "Kitap oku",
//                    4,
//                    20,
//                    false
//                )
//            )
//        )
    }
}
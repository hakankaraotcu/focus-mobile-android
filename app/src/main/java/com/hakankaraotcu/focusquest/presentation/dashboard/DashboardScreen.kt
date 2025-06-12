package com.hakankaraotcu.focusquest.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakankaraotcu.focusquest.R
import com.hakankaraotcu.focusquest.model.Quest
import com.hakankaraotcu.focusquest.presentation.components.questsList
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

@Composable
fun DashboardScreen() {
    val quests = listOf(
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
            true
        )
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Scaffold(
            topBar = { DashboardScreenTopBar() },
            containerColor = Color.Transparent
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                questsList(
                    sectionTitle = "UNCOMPLETED QUESTS",
                    emptyQuestText = "Congratulations, you have finished all the quests.",
                    quests = quests.filter { quest: Quest -> !quest.isComplete }
                )
                questsList(
                    sectionTitle = "COMPLETED QUESTS",
                    emptyQuestText = "Congratulations, you have finished all the quests.",
                    quests = quests.filter { quest: Quest -> quest.isComplete }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreenTopBar() {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = {

            }, modifier = Modifier.size(56.dp)) {
                Box(modifier = Modifier.size(48.dp)) {
                    Image(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.energy),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomEnd),
                        text = "10",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        title = {
            Text(
                text = "Focus Quest",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        actions = {
            IconButton(onClick = {

            }, modifier = Modifier.size(56.dp)) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.level),
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    FocusQuestTheme {
        DashboardScreen()
    }
}
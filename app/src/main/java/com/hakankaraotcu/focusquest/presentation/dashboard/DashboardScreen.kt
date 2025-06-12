package com.hakankaraotcu.focusquest.presentation.dashboard

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hakankaraotcu.focusquest.GameViewModel
import com.hakankaraotcu.focusquest.GameViewModelFactory
import com.hakankaraotcu.focusquest.R
import com.hakankaraotcu.focusquest.domain.model.Quest
import com.hakankaraotcu.focusquest.presentation.components.ConfirmDialog
import com.hakankaraotcu.focusquest.presentation.components.questsList
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

@Composable
fun DashboardScreen() {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: GameViewModel = viewModel(
        factory = GameViewModelFactory(application)
    )
    val quests = viewModel.quests
    val energy = viewModel.getEnergy()
    val level = viewModel.getLevel()
    val exp = viewModel.getExp()
    val expMax = viewModel.getExpForNextLevel()


//    val quests = remember {
//        mutableStateListOf(
//            Quest(
//                1,
//                "Meditasyon yap",
//                1,
//                5,
//                false
//            ),
//            Quest(
//                2,
//                "Spor yap",
//                2,
//                10,
//                false
//            ),
//            Quest(
//                3,
//                "Bir şey öğren",
//                3,
//                15,
//                false
//            ),
//            Quest(
//                4,
//                "Kitap oku",
//                4,
//                20,
//                false
//            )
//        )
//    }

    var isConfirmDialogOpen by rememberSaveable { mutableStateOf(false) }
    var selectedQuestIndex by rememberSaveable { mutableIntStateOf(-1) }

    ConfirmDialog(
        isOpen = isConfirmDialogOpen,
        onDismissRequest = { isConfirmDialogOpen = false },
        onConfirmButtonClick = {
            if (selectedQuestIndex != -1) {
                viewModel.completeQuest(selectedQuestIndex)
                //quests[selectedQuestIndex] = quests[selectedQuestIndex].copy(isComplete = true)
            }
            isConfirmDialogOpen = false
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Scaffold(
            topBar = {
                DashboardScreenTopBar(
                    energy = energy,
                    level = level,
                    exp = exp,
                    maxExp = expMax
                )
            },
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
                    emptyQuestImage = R.drawable.uncomplete,
                    quests = quests.withIndex().filter { !it.value.isComplete },
                    onCompleteButtonClick = { index ->
                        selectedQuestIndex = index
                        isConfirmDialogOpen = true
                    }
                )
                questsList(
                    sectionTitle = "COMPLETED QUESTS",
                    emptyQuestText = "Did you really try today?\nI have a feeling you've gotten a little lazy...",
                    emptyQuestImage = R.drawable.complete,
                    quests = quests.withIndex().filter { it.value.isComplete },
                    onCompleteButtonClick = {
//                        index ->
//                        selectedQuestIndex = index
//                        isConfirmDialogOpen = true
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreenTopBar(energy: Int, level: Int, exp: Int, maxExp: Int) {
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
                        text = "$energy",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Focus Quest",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Level $level - Exp $exp/$maxExp",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
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
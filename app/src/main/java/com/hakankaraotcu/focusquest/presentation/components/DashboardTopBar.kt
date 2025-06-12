package com.hakankaraotcu.focusquest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakankaraotcu.focusquest.R
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(energy: Int, level: Int, exp: Int, maxExp: Int) {
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
//                Text(
//                    text = "Focus Quest",
//                    color = Color.White,
//                    style = MaterialTheme.typography.headlineMedium
//                )
//                Text(
//                    text = "Level $level - Exp $exp/$maxExp",
//                    color = Color.White,
//                    fontSize = 12.sp
//                )
                LevelProgressBar(level = level, exp = exp, expForNextLevel = maxExp)
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
fun DashboardScreenTopBarPreview() {
    FocusQuestTheme {
        DashboardTopBar(10, 1, 0, 10)
    }
}
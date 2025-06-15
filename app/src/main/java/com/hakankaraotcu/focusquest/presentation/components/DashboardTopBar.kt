package com.hakankaraotcu.focusquest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakankaraotcu.focusquest.R
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(level: Int, exp: Int, maxExp: Int) {
    TopAppBar(
        title = {
            Column {
                LevelProgressBar(level = level, exp = exp, expForNextLevel = maxExp)
            }
        },
        actions = {
            IconButton(onClick = {

            }) {
                Image(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.profile),
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
fun DashboardScreenTopBarPreview() {
    FocusQuestTheme {
        DashboardTopBar(1, 0, 10)
    }
}
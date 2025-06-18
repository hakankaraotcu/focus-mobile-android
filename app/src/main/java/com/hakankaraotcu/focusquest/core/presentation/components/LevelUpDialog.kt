package com.hakankaraotcu.focusquest.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.hakankaraotcu.focusquest.R
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme
import kotlinx.coroutines.delay

@Composable
fun LevelUpDialog(
    previousLevel: Int,
    level: Int,
    coinsEarned: Int,
    xpEarned: Int,
    onContinue: () -> Unit
) {
    var showBadge by remember { mutableStateOf(false) }
    var showTitle by remember { mutableStateOf(false) }
    var showRewards by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    val animatedLevel = remember { Animatable(previousLevel.toFloat()) }

    LaunchedEffect(key1 = level) {
        animatedLevel.animateTo(
            targetValue = level.toFloat(),
            animationSpec = tween(durationMillis = 1000)
        )
    }

    LaunchedEffect(Unit) {
        showBadge = true
        delay(200)
        showTitle = true
        delay(200)
        showRewards = true
        delay(200)
        showButton = true
    }

    Dialog(
        onDismissRequest = {
            onContinue()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF3D9)),
            shape = RoundedCornerShape(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(48.dp))

                AnimatedVisibility(
                    visible = showBadge,
                    enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn(tween(1500))
                ) {
                    // Level badge
                    Box(
                        modifier = Modifier
                            .size(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.badge),
                            modifier = Modifier.size(250.dp),
                            contentDescription = "Coin",
                        )
                        Text(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = animatedLevel.value.toInt().toString(),
                            style = MaterialTheme.typography.displayLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                AnimatedVisibility(
                    visible = showTitle,
                    enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn(tween(1500))
                ) {
                    // Level Up Text
                    Text(
                        text = "LEVEL UP!",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(36.dp))

                AnimatedVisibility(
                    visible = showRewards,
                    enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn(tween(1500))
                ) {
                    // Rewards Row
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            modifier = Modifier.size(48.dp),
                            painter = painterResource(R.drawable.quest),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "$coinsEarned New Quests Added",
                            style = MaterialTheme.typography.bodyLarge
                        )

                    }
                }

                Spacer(modifier = Modifier.height(36.dp))

                AnimatedVisibility(
                    visible = showButton,
                    enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn(tween(1500))
                ) {
                    // Continue Button
                    Button(
                        onClick = onContinue,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Continue", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun RewardItem(icon: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LevelUpDialogPreview() {
    FocusQuestTheme {
        LevelUpDialog(7, 1, 556, 7750, onContinue = {})
    }
}
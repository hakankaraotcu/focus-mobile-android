package com.hakankaraotcu.focusquest.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

@Composable
fun LevelProgressBar(
    level: Int,
    exp: Int,
    expForNextLevel: Int
) {
    val targetProgress = exp.toFloat() / expForNextLevel
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 600),
        label = "levelProgress"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
    {
        Text(
            text = "Level $level",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .height(12.dp)
                .background(Color.Gray.copy(alpha = 0.3f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = animatedProgress)
                    .background(Color(0xFFFFCC48))
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Exp $exp / $expForNextLevel",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LevelProgressBarPreview() {
    FocusQuestTheme {
        LevelProgressBar(1, 0, 10)
    }
}
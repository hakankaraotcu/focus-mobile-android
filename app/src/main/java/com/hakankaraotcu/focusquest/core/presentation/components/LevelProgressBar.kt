package com.hakankaraotcu.focusquest.core.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakankaraotcu.focusquest.R
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

@Composable
fun LevelProgressBar(
    level: Int,
    xp: Int,
    xpForNextLevel: Int
) {
    val targetProgress = xp.toFloat() / xpForNextLevel
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 600),
        label = "levelProgress"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(6.dp))
                    .height(16.dp)
                    .background(Color.Gray.copy(alpha = 0.3f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(fraction = animatedProgress)
                        .background(Color(0xFFFFCC48))
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    textAlign = TextAlign.End,
                    text = "$xp / $xpForNextLevel XP",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(R.drawable.star), contentDescription = "Star",
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "$level",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        /*
        Row {
            Text(
                text = "Level $level",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$exp / $expForNextLevel XP",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }

         */
    }
}

@Preview(showBackground = true)
@Composable
fun LevelProgressBarPreview() {
    FocusQuestTheme {
        LevelProgressBar(1, 0, 10)
    }
}
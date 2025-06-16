package com.hakankaraotcu.focusquest.feature_quest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quest(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val level: Int,
    val xpReward: Int,
    val isCompleted: Boolean = false
)

class InvalidQuestException(message: String) : Exception(message)

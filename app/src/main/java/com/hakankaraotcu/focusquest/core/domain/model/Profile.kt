package com.hakankaraotcu.focusquest.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    @PrimaryKey val id: Int? = null,
    val xp: Int = 0,
    val level: Int = 1,
    val xpToNextLevel: Int = level * 10
)

class InvalidProfileException(message: String) : Exception(message)

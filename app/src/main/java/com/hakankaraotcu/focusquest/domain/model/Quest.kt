package com.hakankaraotcu.focusquest.domain.model

data class Quest(
    val id: Int,
    val name: String,
    val expReward: Int,
    var isComplete: Boolean = false
)

package com.hakankaraotcu.focusquest.domain.model

data class Quest(
    val id: Int,
    val name: String,
    val energyCost: Int,
    val expReward: Int,
    var isComplete: Boolean = false
)

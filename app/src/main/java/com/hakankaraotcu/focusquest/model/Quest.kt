package com.hakankaraotcu.focusquest.model

data class Quest(
    val id: Int,
    val name: String,
    val energy: Int,
    val exp: Int,
    var isComplete: Boolean
)

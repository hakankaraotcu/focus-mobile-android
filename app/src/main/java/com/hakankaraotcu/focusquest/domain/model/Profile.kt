package com.hakankaraotcu.focusquest.domain.model

data class Profile(
    var exp: Int = 0,
    var level: Int = 1
) {
    fun expForNextLevel(): Int = level * 10
}

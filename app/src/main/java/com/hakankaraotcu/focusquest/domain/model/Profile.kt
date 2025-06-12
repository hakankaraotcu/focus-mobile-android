package com.hakankaraotcu.focusquest.domain.model

data class Profile(
    var exp: Int = 0,
    var level: Int = 1,
    var energy: Int = 10
) {
    fun expForNextLevel(): Int = level * 10

    fun addExp(amount: Int) {
        exp += amount
        while (exp >= expForNextLevel()) {
            exp -= expForNextLevel()
            level++
        }
    }

    fun consumeEnergy(amount: Int): Boolean {
        if (energy >= amount) {
            energy -= amount
            return true
        }
        return false
    }
}

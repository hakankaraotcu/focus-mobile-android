package com.hakankaraotcu.focusquest.feature_profile.domain.use_case

data class LevelInfo(
    val level: Int,
    val xpRemaining: Int,
    val xpToNextLevel: Int
)

class CalculateLevelFromXp {
    operator fun invoke(totalXp: Int, currentLevel: Int): LevelInfo {
        var level = currentLevel
        var xpRemaining = totalXp
        var xpToNext = level * 10

        while (xpRemaining >= xpToNext) {
            xpRemaining -= xpToNext
            level++
            xpToNext = level * 10
        }

        return LevelInfo(level, xpRemaining, xpToNext)
    }
}
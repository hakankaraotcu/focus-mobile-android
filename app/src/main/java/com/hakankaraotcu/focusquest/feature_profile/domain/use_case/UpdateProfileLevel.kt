package com.hakankaraotcu.focusquest.feature_profile.domain.use_case

import com.hakankaraotcu.focusquest.feature_profile.domain.repository.ProfileRepository

class UpdateProfileLevel(
    private val profileRepository: ProfileRepository,
    private val calculateLevelFromXp: CalculateLevelFromXp
) {
    suspend operator fun invoke() {
        val profile = profileRepository.getProfileById(1)
        profile?.let {
            val levelInfo = calculateLevelFromXp(it.xp, it.level)
            val updated = it.copy(
                xp = levelInfo.xpRemaining,
                level = levelInfo.level,
                xpToNextLevel = levelInfo.xpToNextLevel
            )
            profileRepository.insertProfile(updated)
        }
    }
}
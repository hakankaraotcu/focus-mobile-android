package com.hakankaraotcu.focusquest.feature_profile.domain.use_case

import com.hakankaraotcu.focusquest.core.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.first

class UpdateProfileLevel(
    private val profileRepository: ProfileRepository,
    private val calculateLevelFromXp: CalculateLevelFromXp
) {
    suspend operator fun invoke() {
        val profile = profileRepository.getProfileById(1).first()
            ?: Profile(id = 1, level = 1, xp = 0, xpToNextLevel = 10)

        val levelInfo = calculateLevelFromXp(profile.xp, profile.level)
        val updated = profile.copy(
            xp = levelInfo.xpRemaining,
            level = levelInfo.level,
            xpToNextLevel = levelInfo.xpToNextLevel
        )

        profileRepository.insertProfile(updated)
    }
}
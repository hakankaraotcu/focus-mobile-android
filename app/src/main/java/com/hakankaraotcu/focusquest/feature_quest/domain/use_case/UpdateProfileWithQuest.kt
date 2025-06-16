package com.hakankaraotcu.focusquest.feature_quest.domain.use_case

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.ProfileRepository

class UpdateProfileWithQuest(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(xpEarned: Int) {
        val profile = profileRepository.getProfileById(1)
        val newXp = profile.xp + xpEarned
        var newLevel = profile.level
        var newXpToNext = profile.xpToNextLevel

        // Level up logic
        while (newXp > newXpToNext) {
            newLevel++
            newXpToNext = newLevel * 10
        }

        val updated = Profile(
            id = 1,
            xp = newXp,
            level = newLevel,
            xpToNextLevel = newXpToNext
        )

        profileRepository.insertProfile(updated)
    }
}
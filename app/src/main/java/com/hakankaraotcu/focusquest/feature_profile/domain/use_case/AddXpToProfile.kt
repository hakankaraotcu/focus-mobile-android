package com.hakankaraotcu.focusquest.feature_profile.domain.use_case

import com.hakankaraotcu.focusquest.core.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.first

class AddXpToProfile(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(amount: Int) {
        val profile = profileRepository.getProfileById(1).first()
            ?: Profile(id = 1, level = 1, xp = 0, xpToNextLevel = 10)

        val updated = profile.copy(xp = profile.xp + amount)
        profileRepository.insertProfile(updated)
    }
}
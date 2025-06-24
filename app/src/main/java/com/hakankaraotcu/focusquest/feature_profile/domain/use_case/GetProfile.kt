package com.hakankaraotcu.focusquest.feature_profile.domain.use_case

import com.hakankaraotcu.focusquest.core.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_profile.domain.repository.ProfileRepository

class GetProfile(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(id: Int): Profile {
        val existing = repository.getProfileById(id)
        if (existing != null) return existing

        val defaultProfile = Profile(id = 1, level = 1, xp = 0, xpToNextLevel = 10)
        repository.insertProfile(defaultProfile)
        return defaultProfile
    }
}
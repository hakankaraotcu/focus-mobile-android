package com.hakankaraotcu.focusquest.feature_profile.domain.use_case

import com.hakankaraotcu.focusquest.feature_profile.domain.repository.ProfileRepository

class AddXpToProfile(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(amount: Int) {
        val profile = profileRepository.getProfileById(1)
        profile?.let {
            val updated = it.copy(xp = it.xp + amount)
            profileRepository.insertProfile(updated)
        }
    }
}
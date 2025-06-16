package com.hakankaraotcu.focusquest.feature_quest.domain.use_case

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.ProfileRepository

class GetProfile(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(id: Int): Profile {
        return repository.getProfileById(id)
    }
}
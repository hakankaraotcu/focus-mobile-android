package com.hakankaraotcu.focusquest.feature_profile.domain.use_case

import com.hakankaraotcu.focusquest.core.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetProfile(
    private val repository: ProfileRepository
) {
    operator fun invoke(id: Int): Flow<Profile> = flow {
        val existing = repository.getProfileById(id).first()
        if (existing != null) {
            emitAll(repository.getProfileById(id).filterNotNull())
        } else {
            val defaultProfile = Profile(id = 1, xp = 0, level = 1, xpToNextLevel = 10)
            repository.insertProfile(defaultProfile)
            emit(defaultProfile)
        }
    }
}
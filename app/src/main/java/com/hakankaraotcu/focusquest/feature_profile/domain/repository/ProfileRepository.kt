package com.hakankaraotcu.focusquest.feature_profile.domain.repository

import com.hakankaraotcu.focusquest.core.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfileById(id: Int): Flow<Profile?>

    suspend fun insertProfile(profile: Profile)
}
package com.hakankaraotcu.focusquest.feature_profile.domain.repository

import com.hakankaraotcu.focusquest.feature_profile.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfileById(id: Int): Profile?

    suspend fun insertProfile(profile: Profile)
}
package com.hakankaraotcu.focusquest.feature_quest.domain.repository

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfileById(id: Int): Profile

    suspend fun insertProfile(profile: Profile)
}
package com.hakankaraotcu.focusquest.feature_profile.data.repository

import com.hakankaraotcu.focusquest.feature_profile.data.local.ProfileDao
import com.hakankaraotcu.focusquest.feature_profile.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val dao: ProfileDao
) : ProfileRepository {
    override suspend fun getProfileById(id: Int): Profile? {
        return dao.getProfileById(id)
    }

    override suspend fun insertProfile(profile: Profile) {
        dao.insertProfile(profile)
    }
}
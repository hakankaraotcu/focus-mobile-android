package com.hakankaraotcu.focusquest.feature_profile.data.repository

import com.hakankaraotcu.focusquest.feature_profile.data.local.ProfileDao
import com.hakankaraotcu.focusquest.core.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val dao: ProfileDao
) : ProfileRepository {
    override fun getProfileById(id: Int): Flow<Profile?> {
        return dao.getProfileById(id)
    }

    override suspend fun insertProfile(profile: Profile) {
        dao.insertProfile(profile)
    }
}
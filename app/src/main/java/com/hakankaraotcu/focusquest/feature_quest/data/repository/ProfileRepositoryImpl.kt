package com.hakankaraotcu.focusquest.feature_quest.data.repository

import com.hakankaraotcu.focusquest.feature_quest.data.data_source.ProfileDao
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val dao: ProfileDao
) : ProfileRepository {
    override suspend fun getProfileById(id: Int): Profile {
        return dao.getProfileById(id)
    }

    override suspend fun insertProfile(profile: Profile) {
        dao.insertProfile(profile)
    }
}
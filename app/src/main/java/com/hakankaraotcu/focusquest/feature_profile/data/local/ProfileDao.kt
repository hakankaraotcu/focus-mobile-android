package com.hakankaraotcu.focusquest.feature_profile.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hakankaraotcu.focusquest.feature_profile.domain.model.Profile

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile WHERE id = :id")
    suspend fun getProfileById(id: Int): Profile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: Profile)
}
package com.hakankaraotcu.focusquest.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hakankaraotcu.focusquest.feature_profile.data.local.ProfileDao
import com.hakankaraotcu.focusquest.feature_quest.data.local.QuestDao
import com.hakankaraotcu.focusquest.feature_profile.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest

@Database(
    entities = [Quest::class, Profile::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val questDao: QuestDao
    abstract val profileDao: ProfileDao

    companion object {
        const val DATABASE_NAME = "quests_db"
    }
}
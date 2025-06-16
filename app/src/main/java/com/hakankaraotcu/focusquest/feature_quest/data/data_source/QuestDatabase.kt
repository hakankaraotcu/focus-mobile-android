package com.hakankaraotcu.focusquest.feature_quest.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest

@Database(
    entities = [Quest::class, Profile::class],
    version = 1
)
abstract class QuestDatabase : RoomDatabase() {
    abstract val questDao: QuestDao
    abstract val profileDao: ProfileDao

    companion object {
        const val DATABASE_NAME = "quests_db"
    }
}
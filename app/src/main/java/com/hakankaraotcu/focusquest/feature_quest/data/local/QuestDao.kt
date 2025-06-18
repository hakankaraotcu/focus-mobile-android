package com.hakankaraotcu.focusquest.feature_quest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestDao {

    @Query("SELECT * FROM quest")
    fun getQuests(): Flow<List<Quest>>

    @Query("SELECT * FROM quest WHERE id = :id")
    suspend fun getQuestById(id: Int): Quest?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuest(quest: Quest)
}
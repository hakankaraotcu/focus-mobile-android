package com.hakankaraotcu.focusquest.feature_quest.domain.repository

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import kotlinx.coroutines.flow.Flow

interface QuestRepository {
    fun getQuests(): Flow<List<Quest>>

    suspend fun getQuestById(id: Int): Quest?

    suspend fun insertQuest(quest: Quest)
}
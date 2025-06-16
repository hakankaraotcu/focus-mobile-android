package com.hakankaraotcu.focusquest.feature_quest.data.repository

import com.hakankaraotcu.focusquest.feature_quest.data.data_source.QuestDao
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.QuestRepository
import kotlinx.coroutines.flow.Flow

class QuestRepositoryImpl(
    private val dao: QuestDao
) : QuestRepository {

    override fun getQuests(): Flow<List<Quest>> {
        return dao.getQuests()
    }

    override suspend fun getQuestById(id: Int): Quest?{
        return dao.getQuestById(id)
    }

    override suspend fun insertQuest(quest: Quest) {
        return dao.insertQuest(quest)
    }
}
package com.hakankaraotcu.focusquest.feature_quest.domain.use_case

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.QuestRepository

class GetQuest(
    private val repository: QuestRepository
) {

    suspend operator fun invoke(id: Int): Quest? {
        return repository.getQuestById(id)
    }
}
package com.hakankaraotcu.focusquest.feature_quest.domain.use_case

import com.hakankaraotcu.focusquest.core.util.OrderType
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.QuestRepository
import com.hakankaraotcu.focusquest.feature_quest.domain.util.QuestOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTakenQuests(
    private val repository: QuestRepository
) {
    operator fun invoke(): Flow<List<Quest>> {
        return repository.getTakenQuests()
    }
}
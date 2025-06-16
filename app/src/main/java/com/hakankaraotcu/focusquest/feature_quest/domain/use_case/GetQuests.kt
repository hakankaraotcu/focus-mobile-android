package com.hakankaraotcu.focusquest.feature_quest.domain.use_case

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.QuestRepository
import com.hakankaraotcu.focusquest.feature_quest.domain.util.OrderType
import com.hakankaraotcu.focusquest.feature_quest.domain.util.QuestOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetQuests(
    private val repository: QuestRepository
) {
    operator fun invoke(
        questOrder: QuestOrder = QuestOrder.Xp(OrderType.Ascending)
    ): Flow<List<Quest>> {
        return repository.getQuests().map { quests ->
            when (questOrder.orderType) {
                is OrderType.Ascending -> {
                    when (questOrder) {
                        is QuestOrder.Title -> quests.sortedBy { it.title }
                        is QuestOrder.Level -> quests.sortedBy { it.level }
                        is QuestOrder.Xp -> quests.sortedBy { it.xpReward }
                    }
                }

                is OrderType.Descending -> {
                    when (questOrder) {
                        is QuestOrder.Title -> quests.sortedByDescending { it.title }
                        is QuestOrder.Level -> quests.sortedByDescending { it.level }
                        is QuestOrder.Xp -> quests.sortedByDescending { it.xpReward }
                    }
                }
            }
        }
    }
}
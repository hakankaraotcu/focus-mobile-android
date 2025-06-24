package com.hakankaraotcu.focusquest.feature_quest.domain.use_case

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.QuestRepository
import com.hakankaraotcu.focusquest.core.util.OrderType
import com.hakankaraotcu.focusquest.feature_quest.domain.util.QuestOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllQuests(
    private val repository: QuestRepository
) {
    operator fun invoke(
        userLevel: Int,
        questOrder: QuestOrder = QuestOrder.Xp(OrderType.Ascending)
    ): Flow<List<Quest>> {
        return repository.getAllQuests().map { quests ->
            when (questOrder.orderType) {
                is OrderType.Ascending -> {
                    when (questOrder) {
                        is QuestOrder.Title -> quests.filter { it.levelRequirement <= userLevel }
                            .sortedBy { it.title }

                        is QuestOrder.LevelRequirement -> quests.filter { it.levelRequirement <= userLevel }
                            .sortedBy { it.levelRequirement }

                        is QuestOrder.Level -> quests.filter { it.levelRequirement <= userLevel }
                            .sortedBy { it.level }

                        is QuestOrder.Xp -> quests.filter { it.levelRequirement <= userLevel }
                            .sortedBy { it.xpReward }
                    }
                }

                is OrderType.Descending -> {
                    when (questOrder) {
                        is QuestOrder.Title -> quests.filter { it.levelRequirement <= userLevel }
                            .sortedByDescending { it.title }

                        is QuestOrder.LevelRequirement -> quests.filter { it.levelRequirement <= userLevel }
                            .sortedByDescending { it.levelRequirement }

                        is QuestOrder.Level -> quests.filter { it.levelRequirement <= userLevel }
                            .sortedByDescending { it.level }

                        is QuestOrder.Xp -> quests.filter { it.levelRequirement <= userLevel }
                            .sortedByDescending { it.xpReward }
                    }
                }
            }
        }
    }
}
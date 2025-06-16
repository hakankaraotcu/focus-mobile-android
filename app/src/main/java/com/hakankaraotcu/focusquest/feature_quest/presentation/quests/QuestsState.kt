package com.hakankaraotcu.focusquest.feature_quest.presentation.quests

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.util.OrderType
import com.hakankaraotcu.focusquest.feature_quest.domain.util.QuestOrder

data class QuestsState(
    val completedQuests: List<Quest> = emptyList(),
    val uncompletedQuests: List<Quest> = emptyList(),
    val questOrder: QuestOrder = QuestOrder.Xp(OrderType.Ascending)
)
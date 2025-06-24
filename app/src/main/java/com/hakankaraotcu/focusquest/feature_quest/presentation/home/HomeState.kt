package com.hakankaraotcu.focusquest.feature_quest.presentation.home

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.core.util.OrderType
import com.hakankaraotcu.focusquest.feature_quest.domain.util.QuestOrder

data class HomeState(
    val completedQuests: List<Quest> = emptyList(),
    val uncompletedQuests: List<Quest> = emptyList(),
    val questOrder: QuestOrder = QuestOrder.Xp(OrderType.Ascending)
)
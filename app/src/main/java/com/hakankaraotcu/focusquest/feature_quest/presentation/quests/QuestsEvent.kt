package com.hakankaraotcu.focusquest.feature_quest.presentation.quests

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.util.QuestOrder

sealed class QuestsEvent {
    data class Order(val questOrder: QuestOrder) : QuestsEvent()
    data class Complete(val quest: Quest) : QuestsEvent()
}
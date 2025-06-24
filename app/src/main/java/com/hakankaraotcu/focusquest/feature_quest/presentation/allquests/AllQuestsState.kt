package com.hakankaraotcu.focusquest.feature_quest.presentation.allquests

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest

data class AllQuestsState(
    val availableQuests: List<Quest> = emptyList(),
    val lockedQuests: List<Quest> = emptyList()
)
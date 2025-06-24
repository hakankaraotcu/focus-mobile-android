package com.hakankaraotcu.focusquest.feature_quest.presentation.myquests

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest

data class MyQuestsState(
    val takenQuests: List<Quest> = emptyList()
)
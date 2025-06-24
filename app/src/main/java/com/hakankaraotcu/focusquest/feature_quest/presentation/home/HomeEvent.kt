package com.hakankaraotcu.focusquest.feature_quest.presentation.home

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.util.QuestOrder

sealed class HomeEvent {
    data class Complete(val quest: Quest) : HomeEvent()
}
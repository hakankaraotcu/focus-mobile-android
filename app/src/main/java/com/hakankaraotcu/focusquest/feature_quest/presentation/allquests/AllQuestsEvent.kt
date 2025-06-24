package com.hakankaraotcu.focusquest.feature_quest.presentation.allquests

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest

sealed class AllQuestsEvent {
    data class Take(val quest: Quest): AllQuestsEvent()
    data class Drop(val quest: Quest): AllQuestsEvent()
}
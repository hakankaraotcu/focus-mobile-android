package com.hakankaraotcu.focusquest.feature_quest.presentation.myquests

import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest

sealed class MyQuestsEvent {
    data class Drop(val quest: Quest) : MyQuestsEvent()
}
package com.hakankaraotcu.focusquest.feature_quest.domain.use_case

import com.hakankaraotcu.focusquest.feature_quest.domain.model.InvalidQuestException
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.repository.QuestRepository
import kotlin.jvm.Throws

class UpsertQuest(
    private val repository: QuestRepository
) {

    @Throws(InvalidQuestException::class)
    suspend operator fun invoke(quest: Quest) {
        if (quest.title.isBlank()) {
            throw InvalidQuestException("The title of the quest can't be empty.")
        }
        repository.insertQuest(quest)
    }
}
package com.hakankaraotcu.focusquest.feature_quest.domain.use_case

data class QuestUseCases(
    val getQuests: GetQuests,
    val upsertQuest: UpsertQuest,
    val getQuest: GetQuest
)
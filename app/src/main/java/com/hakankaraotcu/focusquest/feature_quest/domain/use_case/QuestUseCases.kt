package com.hakankaraotcu.focusquest.feature_quest.domain.use_case

data class QuestUseCases(
    val getAllQuests: GetAllQuests,
    val getTakenQuests: GetTakenQuests,
    val upsertQuest: UpsertQuest,
    val getQuest: GetQuest
)
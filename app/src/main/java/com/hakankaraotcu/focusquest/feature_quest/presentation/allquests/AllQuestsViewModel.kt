package com.hakankaraotcu.focusquest.feature_quest.presentation.allquests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.core.util.OrderType
import com.hakankaraotcu.focusquest.feature_profile.domain.use_case.ProfileUseCases
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.QuestUseCases
import com.hakankaraotcu.focusquest.feature_quest.domain.util.QuestOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllQuestsViewModel @Inject constructor(
    private val questUseCases: QuestUseCases,
    private val profileUseCases: ProfileUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(AllQuestsState())
    val uiState: StateFlow<AllQuestsState> = _uiState.asStateFlow()

    private var getAllQuestsJob: Job? = null

    init {
        // Sadece test için!
//        viewModelScope.launch {
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Yatağını topla",
//                    levelRequirement = 1,
//                    xpReward = 5
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Meditasyon yap",
//                    levelRequirement = 1,
//                    xpReward = 10
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Spor yap",
//                    levelRequirement = 1,
//                    xpReward = 15
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Bir şey öğren",
//                    levelRequirement = 1,
//                    xpReward = 20
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Kitap oku",
//                    levelRequirement = 2,
//                    xpReward = 50
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "1000 Adım at",
//                    levelRequirement = 1,
//                    xpReward = 25
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Gitar öğren",
//                    levelRequirement = 2,
//                    xpReward = 40
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Ders çalış",
//                    levelRequirement = 1,
//                    xpReward = 10
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Yapboz yap",
//                    levelRequirement = 1,
//                    xpReward = 20
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Duygularını yaz",
//                    levelRequirement = 1,
//                    xpReward = 30
//                )
//            )
//        }
        getAllQuests()
    }

    fun onEvent(event: AllQuestsEvent) {
        when (event) {
            is AllQuestsEvent.Take -> {
                viewModelScope.launch {
                    val updatedQuest = event.quest.copy(isTaken = true)
                    questUseCases.upsertQuest(updatedQuest)
                }
            }

            is AllQuestsEvent.Drop -> {
                viewModelScope.launch {
                    val updatedQuest = event.quest.copy(isTaken = false)
                    questUseCases.upsertQuest(updatedQuest)
                }
            }
        }
    }

    private fun getAllQuests() {
        getAllQuestsJob?.cancel()
        getAllQuestsJob = viewModelScope.launch {
            val profile = profileUseCases.getProfile(1)

            questUseCases.getAllQuests()
                .collect { quests ->
                    _uiState.update { it ->
                        it.copy(
                            availableQuests = quests.filter { it.levelRequirement <= profile.level },
                            lockedQuests = quests.filter { it.levelRequirement > profile.level }
                        )
                    }
                }
        }
    }
}
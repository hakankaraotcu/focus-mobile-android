package com.hakankaraotcu.focusquest.feature_quest.presentation.quests

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.QuestUseCases
import com.hakankaraotcu.focusquest.core.util.OrderType
import com.hakankaraotcu.focusquest.feature_quest.domain.model.Quest
import com.hakankaraotcu.focusquest.feature_quest.domain.util.QuestOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestsViewModel @Inject constructor(
    private val questUseCases: QuestUseCases,
) : ViewModel() {

    private val _questsState = mutableStateOf(QuestsState())
    val questsState: State<QuestsState> = _questsState

    private var getQuestsJob: Job? = null

    init {
        getQuests(QuestOrder.Xp(OrderType.Ascending))
        // Sadece test için!
//        viewModelScope.launch {
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Yatağını topla",
//                    level = 1,
//                    xpReward = 5,
//                    isCompleted = false
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Meditasyon yap",
//                    level = 1,
//                    xpReward = 10,
//                    isCompleted = false
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Spor yap",
//                    level = 1,
//                    xpReward = 15,
//                    isCompleted = false
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Bir şey öğren",
//                    level = 1,
//                    xpReward = 20,
//                    isCompleted = false
//                )
//            )
//            questUseCases.upsertQuest(
//                Quest(
//                    title = "Kitap oku",
//                    level = 1,
//                    xpReward = 50,
//                    isCompleted = false
//                )
//            )
//        }
    }

    fun onEvent(event: QuestsEvent) {
        when (event) {
            // Sıralama istenirse çalış
            is QuestsEvent.Order -> {
                if (questsState.value.questOrder::class == event.questOrder::class &&
                    questsState.value.questOrder.orderType == event.questOrder.orderType
                ) {
                    return
                }
                getQuests(event.questOrder)
            }

            // Görev tamamlanınca çalış
            is QuestsEvent.Complete -> {
                viewModelScope.launch {
                    val updatedQuest = event.quest.copy(isCompleted = true)
                    questUseCases.upsertQuest(updatedQuest)
                }
            }
        }
    }

    private fun getQuests(questOrder: QuestOrder) {
        getQuestsJob?.cancel()
        getQuestsJob = questUseCases.getQuests(questOrder)
            .onEach { quests ->
                _questsState.value = questsState.value.copy(
                    completedQuests = quests.filter { it.isCompleted },
                    uncompletedQuests = quests.filter { !it.isCompleted },
                    questOrder = questOrder
                )
            }
            .launchIn(viewModelScope)
    }
}
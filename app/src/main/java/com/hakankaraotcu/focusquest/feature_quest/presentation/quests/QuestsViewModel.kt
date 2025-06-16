package com.hakankaraotcu.focusquest.feature_quest.presentation.quests

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.ProfileUseCases
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.QuestUseCases
import com.hakankaraotcu.focusquest.feature_quest.domain.util.OrderType
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
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    private val _state = mutableStateOf(QuestsState())
    val state: State<QuestsState> = _state

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
            is QuestsEvent.Order -> {
                if (state.value.questOrder::class == event.questOrder::class &&
                    state.value.questOrder.orderType == event.questOrder.orderType
                ) {
                    return
                }
                getQuests(event.questOrder)
            }

            is QuestsEvent.Complete -> {
                viewModelScope.launch {
                    val updatedQuest = event.quest.copy(isCompleted = true)
                    questUseCases.upsertQuest(updatedQuest)

                    profileUseCases.updateProfileWithQuest(updatedQuest.xpReward)
                }
            }
        }
    }

    private fun getQuests(questOrder: QuestOrder) {
        getQuestsJob?.cancel()
        getQuestsJob = questUseCases.getQuests(questOrder)
            .onEach { quests ->
                _state.value = state.value.copy(
                    completedQuests = quests.filter { it.isCompleted },
                    uncompletedQuests = quests.filter { !it.isCompleted },
                    questOrder = questOrder
                )
            }
            .launchIn(viewModelScope)
    }
}
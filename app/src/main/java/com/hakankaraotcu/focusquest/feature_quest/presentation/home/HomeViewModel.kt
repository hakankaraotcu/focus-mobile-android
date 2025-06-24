package com.hakankaraotcu.focusquest.feature_quest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.core.util.OrderType
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.QuestUseCases
import com.hakankaraotcu.focusquest.feature_quest.domain.util.QuestOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val questUseCases: QuestUseCases,
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    private var getQuestsJob: Job? = null

    init {
        getQuests(QuestOrder.Xp(OrderType.Ascending))
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Complete -> {
                viewModelScope.launch {
                    val updatedQuest = event.quest.copy(isCompleted = true)
                    questUseCases.upsertQuest(updatedQuest)
                }
            }
        }
    }

    private fun getQuests(questOrder: QuestOrder) {
        getQuestsJob?.cancel()
        getQuestsJob = questUseCases.getTakenQuests()
            .onEach { quests ->
                _homeState.value = _homeState.value.copy(
                    completedQuests = quests.filter { it.isCompleted },
                    uncompletedQuests = quests.filter { !it.isCompleted },
                    questOrder = questOrder
                )
            }
            .launchIn(viewModelScope)
    }
}
package com.hakankaraotcu.focusquest.feature_quest.presentation.myquests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.QuestUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyQuestsViewModel @Inject constructor(
    private val questsUseCases: QuestUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyQuestsState())
    val uiState: StateFlow<MyQuestsState> = _uiState.asStateFlow()

    private var getTakenQuestsJob: Job? = null

    init {
        getTakenQuests()
    }

    fun onEvent(event: MyQuestsEvent) {
        when (event) {
            is MyQuestsEvent.Drop -> {
                viewModelScope.launch {
                    val updatedQuest = event.quest.copy(isTaken = false)
                    questsUseCases.upsertQuest(updatedQuest)
                }
            }
        }
    }

    private fun getTakenQuests() {
        getTakenQuestsJob?.cancel()

        getTakenQuestsJob = viewModelScope.launch {
            questsUseCases.getTakenQuests()
                .collect { quests ->
                    _uiState.update {
                        it.copy(
                            takenQuests = quests
                        )
                    }
                }
        }
    }
}
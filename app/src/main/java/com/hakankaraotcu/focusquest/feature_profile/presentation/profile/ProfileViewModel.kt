package com.hakankaraotcu.focusquest.feature_profile.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.core.domain.model.Profile
import com.hakankaraotcu.focusquest.feature_profile.domain.use_case.ProfileUseCases
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.QuestUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    private val questUseCases: QuestUseCases // For Test Section
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    var previousLevel by mutableIntStateOf(1)
        private set

    init {
        observeProfile()
    }

    private fun observeProfile() {
        viewModelScope.launch {
            profileUseCases.getProfile(1).collect { profile ->
                _uiState.update { currentState ->
                    currentState.copy(
                        profile = profile,
                        xp = profile.xp,
                        xpMax = profile.xpToNextLevel,
                        level = profile.level
                    )
                }
            }
        }
    }

    fun handleQuestCompletion(xpEarned: Int) {
        viewModelScope.launch {
            val currentProfile = _uiState.value.profile ?: return@launch
            val currentXp = currentProfile.xp
            val xpToNext = currentProfile.xpToNextLevel
            val level = currentProfile.level

            if (currentXp + xpEarned >= xpToNext) {
                // Progress barı güncelle
                _uiState.update {
                    it.copy(xp = xpToNext)
                }
                delay(600)

                // Xp ekle ve level atla
                profileUseCases.updateProfileWithQuest(xpEarned)

                // Level up dialog göster
                previousLevel = level
                _uiState.update {
                    it.copy(isLevelUpDialogOpen = true)
                }
            } else {
                // Sadece xp ekle
                profileUseCases.addXpToProfile(xpEarned)
            }
        }
    }

    fun onLevelUpConfirmed() {
        _uiState.update {
            it.copy(isLevelUpDialogOpen = false)
        }
        previousLevel = _uiState.value.level
    }

    // For Test Section
    fun resetData() {
        viewModelScope.launch {
            questUseCases.getAllQuests().collect { quests ->
                quests.forEach { quest ->
                    val updatedQuest = quest.copy(
                        isTaken = false,
                        isCompleted = false,
                    )
                    questUseCases.upsertQuest(updatedQuest)
                }
            }
        }
        viewModelScope.launch {
            val updatedProfile = Profile(id = 1, xp = 0, level = 1, xpToNextLevel = 10)
            profileUseCases.upsertProfile(updatedProfile)
        }
    }
}
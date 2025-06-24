package com.hakankaraotcu.focusquest.feature_profile.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.feature_profile.domain.use_case.ProfileUseCases
import com.hakankaraotcu.focusquest.feature_quest.domain.use_case.QuestUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
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
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            val profile = profileUseCases.getProfile(1)

            _uiState.value = _uiState.value.copy(
                profile = profile,
                xp = profile.xp,
                xpMax = profile.xpToNextLevel,
                level = profile.level
            )
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
                _uiState.value = _uiState.value.copy(
                    xp = xpToNext
                )
                delay(600)

                // Xp ekle ve level atla
                profileUseCases.updateProfileWithQuest(xpEarned)

                // Profil ui güncelle
                updateProfileState(showLevelUpDialog = true, previousLevelValue = level)
            } else {
                // Sadece xp ekle ve profil ui güncelle
                profileUseCases.addXpToProfile(xpEarned)

                // Profil ui güncelle
                updateProfileState()
            }
        }
    }

    fun onLevelUpConfirmed() {
        _uiState.value = _uiState.value.copy(
            isLevelUpDialogOpen = false
        )
        previousLevel = _uiState.value.level
    }

    private suspend fun updateProfileState(
        showLevelUpDialog: Boolean = false,
        previousLevelValue: Int? = null
    ) {
        val updatedProfile = profileUseCases.getProfile(1)

        previousLevelValue?.let {
            previousLevel = it
        }

        _uiState.update { currentState ->
            currentState.copy(
                profile = updatedProfile,
                xp = updatedProfile.xp,
                xpMax = updatedProfile.xpToNextLevel,
                level = updatedProfile.level,
                isLevelUpDialogOpen = showLevelUpDialog
            )
        }
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
            val updatedProfile = profileUseCases.getProfile(1).copy(
                xp = 0, level = 1, xpToNextLevel = 10
            )
            profileUseCases.upsertProfile(updatedProfile)
        }
    }
}
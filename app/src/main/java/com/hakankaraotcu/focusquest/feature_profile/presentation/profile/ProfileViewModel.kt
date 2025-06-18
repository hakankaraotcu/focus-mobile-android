package com.hakankaraotcu.focusquest.feature_profile.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.feature_profile.domain.use_case.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) : ViewModel() {
    private val _uiState = mutableStateOf(ProfileUiState())
    val uiState: State<ProfileUiState> = _uiState

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

                // Görev ile gelen xp'yi profile ekle ve level atla
                profileUseCases.updateProfileWithQuest(xpEarned)

                // Profil ui güncelle
                val updatedProfile = profileUseCases.getProfile(1)

                _uiState.value = _uiState.value.copy(
                    profile = updatedProfile,
                    xp = updatedProfile.xp,
                    xpMax = updatedProfile.xpToNextLevel,
                    level = updatedProfile.level,
                    previousLevel = level, // Önceki level kaydı
                    isLevelUpDialogOpen = true
                )
            } else {
                // Sadece xp ekle ve profil ui güncelle
                profileUseCases.addXpToProfile(xpEarned)
                val updatedProfile = profileUseCases.getProfile(1)

                _uiState.value = _uiState.value.copy(
                    profile = updatedProfile,
                    xp = updatedProfile.xp,
                    xpMax = updatedProfile.xpToNextLevel,
                    level = updatedProfile.level
                )
            }
        }
    }

    fun onLevelUpConfirmed() {
        _uiState.value = _uiState.value.copy(
            isLevelUpDialogOpen = false,
            previousLevel = _uiState.value.level
        )
    }
}
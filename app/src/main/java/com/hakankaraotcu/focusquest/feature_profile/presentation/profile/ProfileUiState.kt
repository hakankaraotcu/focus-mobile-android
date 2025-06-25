package com.hakankaraotcu.focusquest.feature_profile.presentation.profile

import com.hakankaraotcu.focusquest.core.domain.model.Profile

data class ProfileUiState(
    val profile: Profile? = Profile(id = 1, level = 1, xp = 0, xpToNextLevel = 10),
    val xp: Int = 0,
    val xpMax: Int = 10,
    val level: Int = 1,
    val isLevelUpDialogOpen: Boolean = false
)

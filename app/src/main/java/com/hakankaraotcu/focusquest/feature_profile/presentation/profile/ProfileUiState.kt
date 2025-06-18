package com.hakankaraotcu.focusquest.feature_profile.presentation.profile

import com.hakankaraotcu.focusquest.feature_profile.domain.model.Profile

data class ProfileUiState(
    val profile: Profile? = null,
    val xp: Int = 0,
    val xpMax: Int = 0,
    val level: Int = 1,
    val previousLevel: Int = 0,
    val isLevelUpDialogOpen: Boolean = false
)

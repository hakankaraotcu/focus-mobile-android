package com.hakankaraotcu.focusquest.feature_profile.domain.use_case

data class ProfileUseCases(
    val getProfile: GetProfile,
    val addXpToProfile: AddXpToProfile,
    val calculateLevelFromXp: CalculateLevelFromXp,
    val updateProfileLevel: UpdateProfileLevel,
    val updateProfileWithQuest: UpdateProfileWithQuest,
)
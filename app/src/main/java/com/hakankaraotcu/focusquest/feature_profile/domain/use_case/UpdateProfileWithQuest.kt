package com.hakankaraotcu.focusquest.feature_profile.domain.use_case

class UpdateProfileWithQuest(
    private val addXpToProfile: AddXpToProfile,
    private val updateProfileLevel: UpdateProfileLevel
) {
    suspend operator fun invoke(xpEarned: Int) {
        addXpToProfile(xpEarned)
        updateProfileLevel()
    }
}
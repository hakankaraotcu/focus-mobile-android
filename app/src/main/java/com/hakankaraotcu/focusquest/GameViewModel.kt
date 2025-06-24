package com.hakankaraotcu.focusquest

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.core.util.ResetHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class GameViewModel(application: Application) : AndroidViewModel(application) {
    /*
    private val _profile = mutableStateOf(Profile())
    val profile: State<Profile> = _profile

    private var expAnimationJob: Job? = null

    private var remainingExpGlobal = 0
    private var profileSnapshot = _profile.value

    private val _quests = mutableStateOf<List<Quest>>(emptyList())
    val quests: State<List<Quest>> = _quests

    init {
        //checkDailyReset() // sonra aktifleştir şimdilik gerek yok
        //startResetTimer() // test için
        loadSampleQuests()
    }

    private fun loadSampleQuests() {
        _quests.value = listOf(
            Quest(
                1,
                "Yatağını topla",
                5,
                false
            ),
            Quest(
                2,
                "Meditasyon yap",
                10,
                false
            ),
            Quest(
                3,
                "Spor yap",
                15,
                false
            ),
            Quest(
                4,
                "Bir şey öğren",
                20,
                false
            ),
            Quest(
                5,
                "Kitap oku",
                50,
                false
            )
        )
    }

    // test için
    private fun startResetTimer() {
        viewModelScope.launch {
            while (true) {
                delay(5_000L) // her 5 saniyede bir kontrol et
                if (ResetHelper.shouldReset(getApplication())) {
                    resetDailyQuests()
                    ResetHelper.markResetDone(getApplication())
                }
            }
        }
    }

    private fun checkDailyReset() {
        if (ResetHelper.shouldReset(getApplication())) {
            resetDailyQuests()
            ResetHelper.markResetDone(getApplication())
        }
    }

    private fun resetDailyQuests() {
        _quests.value = _quests.value.map { quest ->
            if (quest.isComplete) quest.copy(isComplete = false) else quest
        }
    }

    fun completeQuest(
        index: Int,
        onExpUpdate: (Int, Int, Int) -> Unit,
        onLevelUp: (Int) -> Unit,
        onComplete: () -> Unit
    ) {
        val quest = _quests.value[index]

        if (!quest.isComplete) {
            _quests.value = _quests.value.mapIndexed { i, q ->
                if (i == index) q.copy(isComplete = true) else q
            }

            expAnimationJob?.cancel()
            expAnimationJob = animateExpGain(
                amount = quest.expReward,
                onExpUpdate = onExpUpdate,
                onLevelUp = onLevelUp,
                onComplete = onComplete,
            )
        }
    }

    fun continueExpGain(
        onExpUpdate: (Int, Int, Int) -> Unit,
        onLevelUp: (Int) -> Unit,
        onComplete: () -> Unit
    ) {
        // Devamı varsa, kaldığı yerden devam et
        expAnimationJob = animateExpGain(0, onExpUpdate, onLevelUp, onComplete)
    }

    fun animateExpGain(
        amount: Int,
        onExpUpdate: (Int, Int, Int) -> Unit,
        onLevelUp: (Int) -> Unit,
        onComplete: () -> Unit
    ): Job {
        if (amount > 0) remainingExpGlobal = amount

        return viewModelScope.launch {
            while (remainingExpGlobal > 0) {
                val expForNext = profileSnapshot.expForNextLevel()
                val expToLevelUp = expForNext - profileSnapshot.exp
                val gain = minOf(remainingExpGlobal, expToLevelUp)

                // XP artır
                profileSnapshot = profileSnapshot.copy(exp = profileSnapshot.exp + gain)
                remainingExpGlobal -= gain

                // Önce Xp barı dolsun (eğer seviye atlama gerekiyorsa)
                onExpUpdate(
                    profileSnapshot.level,
                    profileSnapshot.exp,
                    profileSnapshot.expForNextLevel()
                )

                delay(600)

                if (profileSnapshot.exp >= expForNext) {
                    // XP bar doldu, şimdi seviye atla ve sıfırla
                    profileSnapshot = profileSnapshot.copy(
                        level = profileSnapshot.level + 1,
                        exp = 0
                    )

                    _profile.value = profileSnapshot

                    onExpUpdate(
                        profileSnapshot.level,
                        profileSnapshot.exp,
                        profileSnapshot.expForNextLevel()
                    )

                    onLevelUp(profileSnapshot.level)

                    return@launch
                }
            }

            onComplete()
        }
    }

     */
}
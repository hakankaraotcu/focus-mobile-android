package com.hakankaraotcu.focusquest

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.domain.model.Profile
import com.hakankaraotcu.focusquest.domain.model.Quest
import com.hakankaraotcu.focusquest.util.ResetHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val _profile = mutableStateOf(Profile())
    val profile: State<Profile> = _profile

    var quests = mutableStateListOf(
        Quest(
            1,
            "Yatağını topla",
            1,
            5,
            false
        ),
        Quest(
            2,
            "Meditasyon yap",
            2,
            10,
            false
        ),
        Quest(
            3,
            "Spor yap",
            3,
            15,
            false
        ),
        Quest(
            4,
            "Bir şey öğren",
            4,
            20,
            false
        ),
        Quest(
            5,
            "Kitap oku",
            5,
            50,
            false
        )
    )
        private set

    private val maxEnergy = 10

    init {
        //checkDailyReset() // sonra aktifleştir şimdilik gerek yok
        startEnergyRegeneration()
        //startResetTimer() // test için
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
        quests.forEachIndexed { index, quest ->
            if (quest.isComplete) {
                quests[index] = quest.copy(isComplete = false)
            }
        }
    }

    private fun startEnergyRegeneration() {
        viewModelScope.launch {
            while (true) {
                delay(30_000L) // 30 saniye (test için, sonra artacak)
                val current = _profile.value
                if (current.energy < maxEnergy) {
                    _profile.value = current.copy(energy = current.energy + 1)
                }
            }
        }
    }

    fun completeQuest(index: Int, onExpUpdate: (Int, Int, Int) -> Unit, onComplete: () -> Unit) {
        val quest = quests[index]
        val current = _profile.value

        if (!quest.isComplete && current.energy >= quest.energyCost) {
            // Enerji güncelle
            _profile.value = current.copy(
                energy = current.energy - quest.energyCost
            )

            // Görevi tamamla
            quests[index] = quest.copy(isComplete = true)

            animateExpGain(
                amount = quest.expReward,
                onExpUpdate = onExpUpdate,
                onComplete = onComplete
            )
        }
    }

    fun animateExpGain(amount: Int, onExpUpdate: (Int, Int, Int) -> Unit, onComplete: () -> Unit) {
        viewModelScope.launch {
            var remainingExp = amount
            var localProfile = _profile.value

            while (remainingExp > 0) {
                val expForNext = localProfile.expForNextLevel()
                val expToLevelUp = expForNext - localProfile.exp
                val gain = minOf(remainingExp, expToLevelUp)

                // XP artır
                localProfile = localProfile.copy(exp = localProfile.exp + gain)
                remainingExp -= gain

                // Önce Xp barı dolsun (eğer seviye atlama gerekiyorsa)
                onExpUpdate(
                    localProfile.level,
                    localProfile.exp,
                    localProfile.expForNextLevel()
                )

                delay(600)

                if (localProfile.exp >= expForNext) {
                    // XP bar doldu, şimdi seviye atla ve sıfırla
                    localProfile = localProfile.copy(
                        level = localProfile.level + 1,
                        exp = 0
                    )

                    onExpUpdate(
                        localProfile.level,
                        localProfile.exp,
                        localProfile.expForNextLevel()
                    )

                    delay(600)
                }

                _profile.value = localProfile
            }

            onComplete()
        }
    }

    fun getEnergy(): Int = profile.value.energy
    fun getLevel(): Int = profile.value.level
    fun getExp(): Int = profile.value.exp
    fun getExpForNextLevel(): Int = profile.value.expForNextLevel()
}
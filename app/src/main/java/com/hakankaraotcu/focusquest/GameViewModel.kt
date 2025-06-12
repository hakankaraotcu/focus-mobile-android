package com.hakankaraotcu.focusquest

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakankaraotcu.focusquest.domain.model.Profile
import com.hakankaraotcu.focusquest.domain.model.Quest
import com.hakankaraotcu.focusquest.util.ResetHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    var profile by mutableStateOf(Profile())
        private set

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
                delay(30_000L) // 30 saniye
                if (profile.energy < maxEnergy) {
                    profile = profile.copy(energy = profile.energy + 1)
                }
            }
        }
    }

    fun completeQuest(index: Int) {
        val quest = quests[index]
        if (!quest.isComplete && profile.energy >= quest.energyCost) {
            profile = profile.copy() // recomposition trigger
            profile.consumeEnergy(quest.energyCost)
            profile.addExp(quest.expReward)
            quests[index] = quest.copy(isComplete = true)
        }
    }

    fun getEnergy(): Int = profile.energy
    fun getLevel(): Int = profile.level
    fun getExp(): Int = profile.exp
    fun getExpForNextLevel(): Int = profile.expForNextLevel()
}
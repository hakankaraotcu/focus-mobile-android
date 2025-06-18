package com.hakankaraotcu.focusquest.core.util

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ResetHelper {
    private const val PREF_NAME = "daily_reset_prefs"
    private const val LAST_RESET_KEY = "last_reset_date"

    //private const val RESET_INTERVAL_MS = 60_000L // 1 dakika (test için)

    fun shouldReset(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        //val lastReset = prefs.getLong(LAST_RESET_KEY, 0L) // test için
        val lastReset = prefs.getString(LAST_RESET_KEY, null)
        val now = System.currentTimeMillis() // test için

        val today = getCurrentDateString()

        //return (now - lastReset) >= RESET_INTERVAL_MS // test için

        return lastReset != today
    }

    fun markResetDone(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        //prefs.edit().putLong(LAST_RESET_KEY, System.currentTimeMillis()).apply() // test için
        prefs.edit().putString(LAST_RESET_KEY, getCurrentDateString()).apply()
    }

    private fun getCurrentDateString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
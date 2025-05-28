package com.example.calculmentalgame

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ScoreStorage {
    private const val PREFS_NAME = "score_history"
    private const val KEY_HISTORY = "history"

    fun saveScore(context: Context, pseudo: String, score: Int) {
        val history = getHistory(context).toMutableList()
        history.add(0, ScoreEntry(pseudo, score)) // ajout en tête
        val json = Gson().toJson(history)
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_HISTORY, json)
            .apply()
    }

    fun getHistory(context: Context): List<ScoreEntry> {
        val json = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_HISTORY, null) ?: return emptyList()
        val type = object : TypeToken<List<ScoreEntry>>() {}.type
        return Gson().fromJson(json, type)
    }
}

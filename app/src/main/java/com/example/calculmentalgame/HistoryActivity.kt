package com.example.calculmentalgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculmentalgame.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val history = ScoreStorage.getHistory(this)
        val result = history.joinToString("\n") { "${it.pseudo} : ${it.score} pts" }
        binding.textViewHistory.text = result.ifEmpty { "Aucun score enregistré pour le moment." }
    }
}

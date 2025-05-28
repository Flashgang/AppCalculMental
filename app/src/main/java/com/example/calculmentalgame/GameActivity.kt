package com.example.calculmentalgame

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculmentalgame.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private var score = 0
    private var correctAnswer = 0
    private var currentInput = ""
    private var goodStreak = 0
    private var totalTime = 60_000L
    private var currentTimeLeft = totalTime
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startGame()
        setupKeyboard()
    }

    private fun startGame() {
        generateNewQuestion()
        startTimer(currentTimeLeft)
    }

    private fun startTimer(duration: Long) {
        timer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTimeLeft = millisUntilFinished
                val seconds = millisUntilFinished / 1000
                binding.textViewTimer.text = "Temps : $seconds s"
            }

            override fun onFinish() {
                showGameOver()
            }
        }
        timer.start()
    }

    private fun setupKeyboard() {
        val keys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "Effacer", "0", "Valider")
        for (i in 0 until binding.gridKeyboard.childCount) {
            val button = binding.gridKeyboard.getChildAt(i) as Button
            val label = keys[i]

            button.setOnClickListener {
                when (label) {
                    "Effacer" -> {
                        if (currentInput.isNotEmpty()) {
                            currentInput = currentInput.dropLast(1)
                            binding.textViewAnswer.text = currentInput
                        }
                    }
                    "Valider" -> {
                        handleAnswer()
                    }
                    else -> {
                        currentInput += label
                        binding.textViewAnswer.text = currentInput
                    }
                }
            }
        }
    }

    private fun handleAnswer() {
        if (currentInput.isEmpty()) return
        val userAnswer = currentInput.toIntOrNull()
        if (userAnswer == correctAnswer) {
            score++
            goodStreak++
            val bonus = 5000L * goodStreak.coerceAtMost(3)
            currentTimeLeft += bonus
            restartTimer()
            binding.textViewScore.text = "Score : $score"
            currentInput = ""
            binding.textViewAnswer.text = ""
            generateNewQuestion()
        } else {
            goodStreak = 0
            currentTimeLeft -= 5000L
            if (currentTimeLeft <= 0) {
                timer.cancel()
                showGameOver()
            } else {
                restartTimer()
                Toast.makeText(this, "Mauvaise réponse !", Toast.LENGTH_SHORT).show()
                currentInput = ""
                binding.textViewAnswer.text = ""
            }
        }
    }

    private fun restartTimer() {
        timer.cancel()
        startTimer(currentTimeLeft)
    }

    private fun generateNewQuestion() {
        val a = Random.nextInt(1, 20)
        val b = Random.nextInt(1, 20)
        val operators = listOf("+", "-", "×", "÷")
        val op = operators.random()

        val question = when (op) {
            "+" -> { correctAnswer = a + b; "$a + $b" }
            "-" -> { correctAnswer = a - b; "$a - $b" }
            "×" -> { correctAnswer = a * b; "$a × $b" }
            "÷" -> {
                val divisor = if (b == 0) 1 else b
                correctAnswer = a
                "${a * divisor} ÷ $divisor"
            }
            else -> ""
        }
        binding.textViewQuestion.text = question
    }

    private fun showGameOver() {
        val gameOverDialog = GameOverDialog(this, score)
        gameOverDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}

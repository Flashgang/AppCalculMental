package com.example.calculmentalgame

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class GameOverDialog(
    private val context: Context,
    private val score: Int
) {
    fun show() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_game_over, null)
        dialog.setContentView(view)
        dialog.setCancelable(false)

        val textScore = view.findViewById<TextView>(R.id.textViewFinalScore)
        val editTextPseudo = view.findViewById<EditText>(R.id.editTextPseudo)
        val buttonReplay = view.findViewById<Button>(R.id.buttonReplay)
        val buttonExit = view.findViewById<Button>(R.id.buttonExit)

        textScore.text = "Score : $score"

        buttonReplay.setOnClickListener {
            val pseudo = editTextPseudo.text.toString().trim()
            if (pseudo.isEmpty()) {
                Toast.makeText(context, "Veuillez entrer un pseudo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(context, "Bravo $pseudo ! Score : $score", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
            if (context is GameActivity) context.finish()

            ScoreStorage.saveScore(context, pseudo, score)

        }

        buttonExit.setOnClickListener {
            dialog.dismiss()
            if (context is GameActivity) context.finish()
        }

        dialog.show()
    }
}

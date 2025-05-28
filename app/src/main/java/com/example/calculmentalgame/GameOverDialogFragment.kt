package com.example.calculmentalgame

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.calculmentalgame.databinding.DialogGameOverBinding

class GameOverDialogFragment(private val score: Int, private val onSubmit: (String) -> Unit) : DialogFragment() {

    private lateinit var binding: DialogGameOverBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogGameOverBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireContext())
            .setTitle("Fin de partie")
            .setView(binding.root)
            .setCancelable(false)
            .setPositiveButton("Valider") { _, _ ->
                val pseudo = binding.editTextPseudo.text.toString()
                onSubmit(pseudo)
            }
            .create()
    }
}

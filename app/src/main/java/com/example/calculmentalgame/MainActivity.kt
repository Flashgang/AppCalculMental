package com.example.calculmentalgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.calculmentalgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val lang = getSharedPreferences("settings", MODE_PRIVATE).getString("lang", "fr") ?: "fr"
        LocaleHelper.setLocale(this, lang)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPlay.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        binding.buttonAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        binding.buttonLanguage.setOnClickListener {
            showLanguageSelector()
        }

        binding.buttonHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }

    private fun showLanguageSelector() {
        val languages = arrayOf(
            getString(R.string.french),
            getString(R.string.portuguese),
            getString(R.string.arabic)
        )
        val codes = arrayOf("fr", "pt", "ar")

        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.select_language))
        builder.setItems(languages) { _, which ->
            val selectedLang = codes[which]
            getSharedPreferences("settings", MODE_PRIVATE).edit()
                .putString("lang", selectedLang)
                .apply()
            LocaleHelper.setLocale(this, selectedLang)
            val intent = intent
            finish()
            startActivity(intent)
        }
        builder.show()
    }
}

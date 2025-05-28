package com.example.calculmentalgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculmentalgame.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val lang = getSharedPreferences("settings", MODE_PRIVATE).getString("lang", "fr") ?: "fr"
        LocaleHelper.setLocale(this, lang)

        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewTeam.text = getString(R.string.team_members)
        binding.textViewFeatures.text = getString(R.string.features_list)
    }
}
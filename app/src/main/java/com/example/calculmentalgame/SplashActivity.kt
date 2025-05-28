package com.example.calculmentalgame

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.calculmentalgame.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Appliquer la langue choisie
        val lang = getSharedPreferences("settings", MODE_PRIVATE).getString("lang", "fr") ?: "fr"
        LocaleHelper.setLocale(this, lang)

        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Animation simple sur le logo/titre
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.splashText.startAnimation(animation)

        // Redirection après 2,5 secondes
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2500)
    }
}

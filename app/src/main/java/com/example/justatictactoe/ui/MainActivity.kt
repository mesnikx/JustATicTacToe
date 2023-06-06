package com.example.justatictactoe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.justatictactoe.R

class MainActivity : AppCompatActivity() {

    lateinit var ivLogo:ImageView
    lateinit var tvChoseMode:TextView
    lateinit var buttonPlayWithFriend:Button
    lateinit var buttonPlayWithAI:Button
    lateinit var ivSettings: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        initialiseViews()
    }

    private fun initialiseViews() {
        ivLogo = findViewById(R.id.ivAppLogo)
        tvChoseMode = findViewById(R.id.tvChooseMode)
        buttonPlayWithFriend = findViewById(R.id.buttonPlayWithFriend)
        buttonPlayWithAI = findViewById(R.id.buttonPlayWithAI)
        ivSettings = findViewById(R.id.ivSettings)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        buttonPlayWithFriend.setOnClickListener{
            val intent = Intent(this@MainActivity, PlayerDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.justatictactoe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.justatictactoe.R
import com.example.justatictactoe.utils.AppConstants
import com.example.justatictactoe.utils.Utility
import com.google.android.material.textfield.TextInputEditText

class PlayerDetailsActivity : AppCompatActivity() {

    lateinit var ibClosePlayerDetailsScreen: ImageButton
    lateinit var enterUserName: TextInputEditText
    lateinit var buttonNextAfterPlayerDetails: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)

        initaliseViews()
    }

    private fun initaliseViews() {
        ibClosePlayerDetailsScreen = findViewById(R.id.ClosePlayerDetailsScreen)
        enterUserName = findViewById(R.id.enterUserName)
        buttonNextAfterPlayerDetails = findViewById(R.id.buttonNextAfterPlayerDetails)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        ibClosePlayerDetailsScreen.setOnClickListener {
            finish()
        }
        buttonNextAfterPlayerDetails.setOnClickListener {
            redirectToChoseYourSideActivity()
        }
    }

    private fun redirectToChoseYourSideActivity() {
        val userName = enterUserName.text.toString()
        if(userName.isNullOrEmpty()) {
            enterUserName.error = getString(R.string.please_enter_valid_name)
            Toast.makeText(this, getString(R.string.please_enter_valid_name), Toast.LENGTH_SHORT).show()
        } else {
            Utility.hideKeyboard(this@PlayerDetailsActivity)
            startActivity(Intent(this@PlayerDetailsActivity, FriendDetailsActivity::class.java).apply {
                putExtra(AppConstants.USERNAME, userName)
            })
        }
    }
}
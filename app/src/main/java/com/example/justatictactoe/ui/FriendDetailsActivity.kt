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

class FriendDetailsActivity : AppCompatActivity() {

    lateinit var ibCloseFriendDetailsScreen: ImageButton
    lateinit var enterFriendName: TextInputEditText
    lateinit var buttonNextAfterFriendDetails: Button

    private var username:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_details)

        initaliseViews()
    }

    private fun initaliseViews() {
        ibCloseFriendDetailsScreen = findViewById(R.id.ibCloseFriendDetailsScreen)
        enterFriendName = findViewById(R.id.enterFriendName)
        buttonNextAfterFriendDetails = findViewById(R.id.buttonNextAfterFriendDetails)

        username = intent?.getStringExtra(AppConstants.USERNAME)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        ibCloseFriendDetailsScreen.setOnClickListener {
            finish()
        }
        buttonNextAfterFriendDetails.setOnClickListener {
            redirectToChoseYourSideActivity()
        }
    }

    private fun redirectToChoseYourSideActivity() {
        val friendName = enterFriendName.text.toString()
        if(friendName.isNullOrEmpty()) {
            enterFriendName.error = getString(R.string.please_enter_valid_name)
            Toast.makeText(this, getString(R.string.please_enter_valid_name), Toast.LENGTH_SHORT).show()
        } else {
            Utility.hideKeyboard(this@FriendDetailsActivity)
            startActivity(Intent(this@FriendDetailsActivity, ChooseYourSideActivity::class.java).apply {
                putExtra(AppConstants.USERNAME, username)
                putExtra(AppConstants.USER_FRIEND_NAME, friendName)
            })
        }
    }
}
package com.example.justatictactoe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.justatictactoe.R
import com.example.justatictactoe.utils.AppConstants

class ChooseYourSideActivity : AppCompatActivity() {

    lateinit var ivChooseYourSideActivity: ImageView
    lateinit var llSidesContainer: LinearLayout
    lateinit var rgChooseSide: RadioGroup
    lateinit var tvYourSelectedSide: TextView
    lateinit var tvGreetUser: TextView
    lateinit var buttonNextAfterChoosingSide: Button

    var username: String? = null
    var friendName: String? = null
    var yourSide: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_your_side)

        username = intent?.getStringExtra(AppConstants.USERNAME) ?: ""
        friendName = intent?.getStringExtra(AppConstants.USER_FRIEND_NAME) ?: ""
        initialiseViews()
    }

    private fun initialiseViews() {
        ivChooseYourSideActivity = findViewById(R.id.ivChooseYourSideActivity)
        llSidesContainer = findViewById(R.id.llSidesContainer)
        tvGreetUser = findViewById(R.id.tvGreetUser)
        rgChooseSide = findViewById(R.id.rgChooseSide)
        tvYourSelectedSide = findViewById(R.id.tvYourSelectedSide)
        buttonNextAfterChoosingSide = findViewById(R.id.buttonNextAfterChoosingSide)

        username?.also {
            tvGreetUser.text = "Hello $it :)"
        }
        setupClickListeners()
    }

    private fun setupClickListeners() {

        ivChooseYourSideActivity.setOnClickListener {
            finish()
        }

        rgChooseSide.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.rbX) {
                sideSelected(AppConstants.TEAM_X)
            } else if (checkedId == R.id.rbO) {
                sideSelected(AppConstants.TEAM_O)
            }
        }

        buttonNextAfterChoosingSide.setOnClickListener {
            if (yourSide.isNullOrEmpty()) {
                Toast.makeText(this, getString(R.string.choose_a_side), Toast.LENGTH_SHORT).show()
            } else {
                goToGameActivity()
            }
        }
    }

    private fun goToGameActivity() {
        startActivity(
            Intent(this@ChooseYourSideActivity, GameActivity::class.java).apply {
                putExtra(AppConstants.USERNAME, username)
                putExtra(AppConstants.USER_TEAM, yourSide)
                putExtra(AppConstants.USER_FRIEND_NAME, friendName)
            },
        )
    }

    private fun sideSelected(sideValue: String) {
        yourSide = sideValue
        tvYourSelectedSide.text = "${getString(R.string.you_are_team_o)} $yourSide"

        Toast.makeText(this, getString(R.string.all_set), Toast.LENGTH_SHORT).show()
        buttonNextAfterChoosingSide.apply {
            background = getDrawable(R.drawable.ic_play_button_shape)
            text = getString(R.string.play)
            setTextColor(getColor(R.color.white))
        }

    }

}


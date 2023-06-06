package com.example.justatictactoe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.justatictactoe.R
import com.example.justatictactoe.utils.AppConstants
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {

    private var userside: String? = null
    private var winnerMsg: String? = null
    lateinit var tvWinnerMessage: TextView
    lateinit var btPlayAgain: Button
    lateinit var tvWinningSide: TextView
    lateinit var ivWinningSide: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setupUI()
    }

    private fun setupUI() {
        winnerMsg = intent?.getStringExtra(AppConstants.WINNER_MESSAGE) ?: ""
        userside = intent?.getStringExtra(AppConstants.USER_TEAM) ?: ""


        tvWinnerMessage = findViewById(R.id.tvWinnerMessage)
        btPlayAgain = findViewById(R.id.btPlayAgain)
        tvWinningSide = findViewById(R.id.tvWinningSide)
        ivWinningSide = findViewById(R.id.ivWinningSide)

        setOnClickListeners()
        tvWinnerMessage.text = winnerMsg

        if (userside == AppConstants.TEAM_X) {
            tvWinningSide.text = AppConstants.TEAM_X
            ivWinningSide.setImageDrawable(getDrawable(R.drawable.ic_x))
        } else {
            tvWinningSide.text = AppConstants.TEAM_O
            ivWinningSide.setImageDrawable(getDrawable(R.drawable.ic_o))
        }
    }

    private fun setOnClickListeners() {
        btPlayAgain.setOnClickListener{
            startActivity(
                Intent(this@ResultActivity, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }
    }
}
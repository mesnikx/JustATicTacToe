package com.example.justatictactoe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.justatictactoe.R
import com.example.justatictactoe.utils.AppConstants
import com.example.justatictactoe.utils.GameRules

class GameActivity : AppCompatActivity(), OnClickListener {

    private var userside: String? = null
    private var username: String? = null
    private var friendName: String? = null

    lateinit var ivCloseGameActivity: ImageView

    lateinit var tvFriendOrAISteps: TextView
    lateinit var ivFriendOrAI: ImageView
    lateinit var tvFriendOrAIName: TextView

    lateinit var ivFirst: ImageView
    lateinit var ivSecond: ImageView
    lateinit var ivThird: ImageView
    lateinit var ivFourth: ImageView
    lateinit var ivFifth: ImageView
    lateinit var ivSixth: ImageView
    lateinit var ivSeventh: ImageView
    lateinit var ivEighth: ImageView
    lateinit var ivNinth: ImageView

    lateinit var ivUser: ImageView
    lateinit var tvUsername: TextView
    lateinit var tvUserSteps: TextView

    lateinit var rlUserContainer: RelativeLayout
    lateinit var rlFriendOrAIContainer: RelativeLayout

    lateinit var tvGameResult: TextView

    var playingWithFriend: Boolean = false

    private var userSideMapping = Pair(-1, "")

    private val imageBoxes = listOf(
        R.id.ivFirst, R.id.ivSecond, R.id.ivThird, R.id.ivFourth, R.id.ivFifth,
        R.id.ivSixth, R.id.ivSeventh, R.id.ivEighth, R.id.ivNinth
    )

    private val filledPositions = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

    private val playerUser = 0
    private val playerFriendOrAI = 1

    private var isGameInProgress = false
    private var activePlayer = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        initialiseViews()
    }

    private fun initialiseViews() {
        ivCloseGameActivity = findViewById(R.id.ivCloseGameActivity)
        tvFriendOrAISteps = findViewById(R.id.tvFriendOrAISteps)
        ivFriendOrAI = findViewById(R.id.ivFriendOrAI)
        tvFriendOrAIName = findViewById(R.id.tvFriendOrAIName)
        ivFirst = findViewById(R.id.ivFirst)
        ivSecond = findViewById(R.id.ivSecond)
        ivThird = findViewById(R.id.ivThird)
        ivFourth = findViewById(R.id.ivFourth)
        ivFifth = findViewById(R.id.ivFifth)
        ivSixth = findViewById(R.id.ivSixth)
        ivSeventh = findViewById(R.id.ivSeventh)
        ivEighth = findViewById(R.id.ivEighth)
        ivNinth = findViewById(R.id.ivNinth)
        ivUser = findViewById(R.id.ivUser)
        tvUsername = findViewById(R.id.tvUsername)
        tvUserSteps = findViewById(R.id.tvUserSteps)

        rlUserContainer = findViewById(R.id.rlUserContainer)
        rlFriendOrAIContainer = findViewById(R.id.rlFriendOrAIContainer)

        tvGameResult = findViewById(R.id.tvGameResult)

        setupUI()

        setupClickListeners()
    }

    private fun setupUI() {
        username = intent?.getStringExtra(AppConstants.USERNAME)
        userside = intent?.getStringExtra(AppConstants.USER_TEAM)
        friendName = intent?.getStringExtra(AppConstants.USER_FRIEND_NAME)

        userside?.also {
            if (it == AppConstants.TEAM_X) {
                activePlayer = 0
                userSideMapping = Pair(0, "USER")
            } else if (it == AppConstants.TEAM_O) {
                activePlayer = 1
                userSideMapping = Pair(1, "USER")
            }
        }

        username?.also {
            tvUsername.text = it
        }
        friendName?.also {
            playingWithFriend = true
            tvFriendOrAIName.text = it
        }
        val image = if (playingWithFriend) R.drawable.ic_face else R.drawable.ic_robot
        ivFriendOrAI.setImageDrawable(getDrawable(image))

        rlUserContainer.background = getDrawable(R.drawable.ic_active_indicator)
    }


    private fun setupClickListeners() {
        ivCloseGameActivity.setOnClickListener {
            finish()
        }

        ivFirst.setOnClickListener(this)
        ivSecond.setOnClickListener(this)
        ivThird.setOnClickListener(this)
        ivFourth.setOnClickListener(this)
        ivFifth.setOnClickListener(this)
        ivSixth.setOnClickListener(this)
        ivSeventh.setOnClickListener(this)
        ivEighth.setOnClickListener(this)
        ivNinth.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        Log.d(TAG, "ComingHere_Inside_onClick")

        val clickedImage = view?.let { findViewById<ImageView>(it.id) }
        val clickedImageTag = view?.tag?.toString()?.toInt() ?: 0
        val position = clickedImageTag - 1
        Log.d(TAG, "Clicked Image TAG = $clickedImageTag")

        if (activePlayer == playerUser && filledPositions[position] == -1) {
            clickedImage?.setImageDrawable(getDrawable(R.drawable.ic_x))

            filledPositions[position] = playerUser

            if(GameRules.checkForWin(filledPositions)) {
                gameComplete(activePlayer)
            } else {
                activePlayer = playerFriendOrAI
                makeContainerActive(activeViewGroup = rlUserContainer, disableViewGroup = rlFriendOrAIContainer)
            }
        } else if (activePlayer == playerFriendOrAI && filledPositions[position] == -1) {
            clickedImage?.setImageDrawable(getDrawable(R.drawable.ic_o))
            filledPositions[position] = playerFriendOrAI

            if(GameRules.checkForWin(filledPositions)) {
                gameComplete(activePlayer)
            } else {
                activePlayer = playerUser
                makeContainerActive(activeViewGroup = rlFriendOrAIContainer, disableViewGroup = rlUserContainer)
            }

        }
    }

    private fun gameComplete(activePlayer: Int) {
        Log.d(TAG, "Coming_Inside_gameComplete, activePlayer= $activePlayer")
        Log.d(TAG, "Coming_Inside_gameComplete userMapping= $userSideMapping")

        val whoWon = if (userSideMapping.first == activePlayer) {
            AppConstants.User
        } else {
            AppConstants.FriendOrAI
        }

        val winnerName = if ((whoWon == AppConstants.User) && !username.isNullOrEmpty()) {
            username
        } else if((whoWon == AppConstants.FriendOrAI) && !friendName.isNullOrEmpty()) {
            friendName
        } else {
            "GG"
        }

        tvGameResult.text = "$winnerName ${getString(R.string.game_won)}"

        startActivity(Intent(this@GameActivity, ResultActivity::class.java).apply {
            putExtra(AppConstants.WINNER_MESSAGE, "$winnerName ${getString(R.string.game_won)}")
            putExtra(AppConstants.USER_TEAM, userside)
        })

    }



    private fun makeContainerActive(activeViewGroup: RelativeLayout, disableViewGroup: RelativeLayout) {
        disableViewGroup.background = null
        activeViewGroup.background = getDrawable(R.drawable.ic_active_indicator)

    }
    companion object {
        private val TAG = GameActivity::class.java.simpleName
    }
}
package com.dev.nereya.ui_game_project

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dev.nereya.ui_game_project.interfaces.CallbackHighScoreClicked
import com.dev.nereya.ui_game_project.ui.HighScoreFragment
import com.dev.nereya.ui_game_project.ui.MapFragment
import com.dev.nereya.ui_game_project.utils.Constants
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView


class ScoreActivity : AppCompatActivity() {

    private lateinit var main_FRAME_list: FrameLayout
    private lateinit var main_FRAME_map: FrameLayout
    private lateinit var mapFragment: MapFragment
    private lateinit var highScoreFragment: HighScoreFragment
    private lateinit var score_LBL_score: MaterialTextView

    private lateinit var score_BTN_save: MaterialButton
    private lateinit var score_container: LinearLayoutCompat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViews()
        initViews()

    }


    private fun findViews() {
        main_FRAME_list = findViewById(R.id.score_FRAME_list)
        main_FRAME_map = findViewById(R.id.score_FRAME_map)
        score_LBL_score = findViewById(R.id.score_LBL_score)
        score_BTN_save = findViewById(R.id.score_BTN_save)
        score_container = findViewById(R.id.score_container)
    }

    private fun initViews() {
        val bundle: Bundle? = intent.extras

        val score = bundle?.getString(Constants.BundleKeys.SCORE_KEY, "-")
        score_LBL_score.text = score
        val mode = intent.getStringExtra("GameMode")

        if (mode == "leaderboard") {
            score_container.visibility = View.GONE
        } else {
            score_container.visibility = View.VISIBLE
        }

        score_BTN_save.setOnClickListener {
            score_container.visibility = View.GONE
        }

        mapFragment = MapFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.score_FRAME_map, mapFragment)
            .commit()

        highScoreFragment = HighScoreFragment()
        HighScoreFragment.highScoreItemClicked =
            object : CallbackHighScoreClicked {
                override fun highScoreItemClicked(lat: Double, lon: Double) {
                    mapFragment.zoom(lat, lon)
                }
            }

        supportFragmentManager
            .beginTransaction()
            .add(R.id.score_FRAME_list, highScoreFragment)
            .commit()
    }
}
package com.dev.nereya.ui_game_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.dev.nereya.ui_game_project.R
import com.dev.nereya.ui_game_project.interfaces.CallbackHighScoreClicked
import com.dev.nereya.ui_game_project.model.LeaderBoardList
import com.dev.nereya.ui_game_project.utils.Constants
import com.dev.nereya.ui_game_project.utils.SharedPreferencesManager
import com.google.android.material.textview.MaterialTextView
import com.google.gson.Gson


class HighScoreFragment : Fragment() {

    private var highScoreNames: Array<MaterialTextView>? = null
    private var highScorePoints: Array<MaterialTextView>? = null
    private var highScoreContainers:Array<CardView>? = null

    private lateinit var LeaderBoard: LeaderBoardList

    companion object {
        var highScoreItemClicked: CallbackHighScoreClicked? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.fragment_high_score, container, false)
        loadLeaderboard()

        findViews(v)
        initViews()
        return v
    }

    private fun loadLeaderboard() {
        val gson = Gson()
        val leaderBoardFromSP: String = SharedPreferencesManager
            .getInstance()
            .getString(Constants.SPKeys.LEADERBOARD_KEY, "")

        LeaderBoard = if (leaderBoardFromSP.isEmpty()) {
            LeaderBoardList()
        } else {
            gson.fromJson(leaderBoardFromSP, LeaderBoardList::class.java) ?: LeaderBoardList()
        }
    }

    private fun initViews() {
        val dataList = LeaderBoard.leaderBoard.sortedByDescending {
            it.playerScore
        }

        for (i in 0..9) {
            if (i < dataList.size) {
                val player = dataList[i]

                highScoreContainers?.get(i)?.apply {
                    setOnClickListener {
                        val lat = player.lat
                        val lon = player.lon

                        highScoreItemClicked?.highScoreItemClicked(lat, lon)
                    }
                }
                highScoreNames?.get(i)?.apply {
                    visibility = View.VISIBLE
                    text = "${player.playerName}"
                }
                highScorePoints?.get(i)?.apply {
                    visibility = View.VISIBLE
                    text = "${player.playerScore}"
                }
            } else {
                highScoreNames?.get(i)?.visibility = View.INVISIBLE
            }
        }
    }

    private fun findViews(v: View) {
        highScoreNames = arrayOf(
            v.findViewById(R.id.highScore_name_1),
            v.findViewById(R.id.highScore_name_2),
            v.findViewById(R.id.highScore_name_3),
            v.findViewById(R.id.highScore_name_4),
            v.findViewById(R.id.highScore_name_5),
            v.findViewById(R.id.highScore_name_6),
            v.findViewById(R.id.highScore_name_7),
            v.findViewById(R.id.highScore_name_8),
            v.findViewById(R.id.highScore_name_9),
            v.findViewById(R.id.highScore_name_10)
        )
        highScorePoints = arrayOf(
            v.findViewById(R.id.highScore_score_1),
            v.findViewById(R.id.highScore_score_2),
            v.findViewById(R.id.highScore_score_3),
            v.findViewById(R.id.highScore_score_4),
            v.findViewById(R.id.highScore_score_5),
            v.findViewById(R.id.highScore_score_6),
            v.findViewById(R.id.highScore_score_7),
            v.findViewById(R.id.highScore_score_8),
            v.findViewById(R.id.highScore_score_9),
            v.findViewById(R.id.highScore_score_10)
        )
         highScoreContainers= arrayOf(
            v.findViewById(R.id.highScore_container1),
            v.findViewById(R.id.highScore_container2),
            v.findViewById(R.id.highScore_container3),
            v.findViewById(R.id.highScore_container4),
            v.findViewById(R.id.highScore_container5),
            v.findViewById(R.id.highScore_container6),
            v.findViewById(R.id.highScore_container7),
            v.findViewById(R.id.highScore_container8),
            v.findViewById(R.id.highScore_container9),
            v.findViewById(R.id.highScore_container10))
    }
}
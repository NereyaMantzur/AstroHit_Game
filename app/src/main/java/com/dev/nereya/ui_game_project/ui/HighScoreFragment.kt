package com.dev.nereya.ui_game_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.nereya.ui_game_project.R
import com.dev.nereya.ui_game_project.interfaces.Callback_HighScoreClicked
import com.google.android.material.textview.MaterialTextView


class HighScoreFragment : Fragment() {

    private var highScoreList: Array<MaterialTextView>? = null

    companion object {
        var highScoreItemClicked: Callback_HighScoreClicked? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_high_score, container, false)
        findViews(v)
        initViews()
        return v
    }

    private fun findViews(v: View) {
        highScoreList = arrayOf(
            v.findViewById(R.id.highScore_text_1),
            v.findViewById(R.id.highScore_text_2),
            v.findViewById(R.id.highScore_text_3),
            v.findViewById(R.id.highScore_text_4),
            v.findViewById(R.id.highScore_text_5),
            v.findViewById(R.id.highScore_text_6),
            v.findViewById(R.id.highScore_text_7),
            v.findViewById(R.id.highScore_text_8),
            v.findViewById(R.id.highScore_text_9),
            v.findViewById(R.id.highScore_text_10)
        )
    }

    private fun initViews() {

        for (i in 0..9) {
            highScoreList?.get(i)?.setOnClickListener {
                val text = highScoreList?.get(i)?.text.toString()
                val coordinates = text.split(",")
                val lat = coordinates.getOrNull(0)?.toDoubleOrNull() ?: 0.0
                val lon = coordinates.getOrNull(1)?.toDoubleOrNull() ?: 0.0

                highScoreItemClicked?.highScoreItemClicked(lat, lon)
            }
        }
    }
}
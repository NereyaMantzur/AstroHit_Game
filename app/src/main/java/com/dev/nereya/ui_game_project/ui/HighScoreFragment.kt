package com.dev.nereya.ui_game_project.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.nereya.ui_game_project.R
import com.dev.nereya.ui_game_project.interfaces.Callback_HighScoreClicked
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText



class HighScoreFragment : Fragment() {

    private var highScore_ET_text: TextInputEditText? = null
    private var highScore_BTN_send: MaterialButton? = null

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
        highScore_ET_text = v.findViewById(R.id.highScore_ET_text)
        highScore_BTN_send = v.findViewById(R.id.highScore_BTN_send)
    }

    private fun initViews() {
        highScore_BTN_send?.setOnClickListener {
            val text = highScore_ET_text?.text.toString()
            val coordinates = text.split(",")
            val lat = coordinates.getOrNull(0)?.toDoubleOrNull() ?: 0.0
            val lon = coordinates.getOrNull(1)?.toDoubleOrNull() ?: 0.0

            highScoreItemClicked?.highScoreItemClicked(lat, lon)
        }
    }
}
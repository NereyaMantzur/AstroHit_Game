package com.dev.nereya.ui_game_project.model

import android.R
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.dev.nereya.ui_game_project.utils.AsteroidState
import com.dev.nereya.ui_game_project.utils.SignalManager

class GameManager(spaceships: Int = 3) {

    var isGameEnded: Boolean = false
    var score: Int = 0
    var buttonClicked: Boolean = true
    var currentShipIndex: Int = 0
    var hits: Int = 0
    var hearts = 3

    fun checkCollision(
        state: AsteroidState,
        spaceships: Array<AppCompatImageView>,
        heartsArray: Array<AppCompatImageView>
    ) {
        val shipRowIndex = state.rowStart / 5

        if (state.colIndex == 4 && spaceships[shipRowIndex].visibility == View.VISIBLE) {
            SignalManager.getInstance(spaceships[shipRowIndex].context).vibrate()
            SignalManager.getInstance(spaceships[shipRowIndex].context).toast("OUCH")
            hearts--
            hits++

            if (hearts >= 0) {
                heartsArray[hearts].visibility = View.INVISIBLE
            }

            if (hearts == 0) {
                isGameEnded = true
            }
        }
    }
}
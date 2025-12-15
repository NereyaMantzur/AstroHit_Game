package com.dev.nereya.ui_game_project.model

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
        shipIndex: Int
    ): Boolean {
        val shipRowIndex = state.rowStart / 5
        if (state.colIndex == 4 && shipRowIndex == shipIndex) {
            hearts--
            hits++
            if (hearts == 0) {
                isGameEnded = true
            }
            return true
        }
        return false
    }
}
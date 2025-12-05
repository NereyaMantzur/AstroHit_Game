package com.dev.nereya.ui_game_project.model

import android.R
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.dev.nereya.ui_game_project.utils.AsteroidState

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
        heartsArray: Array<AppCompatImageView> // Add this parameter
    ) {
        val shipRowIndex = state.rowStart / 5

        // Check if Asteroid hit the Danger Line AND the ship is there
        if (state.colIndex == 4 && spaceships[shipRowIndex].visibility == View.VISIBLE) {

            // 1. Logic: Reduce health
            hearts--
            hits++

            // 2. UI: Hide the specific heart we just lost
            // We check (hearts >= 0) to prevent crashing if hearts is -1
            if (hearts >= 0) {
                heartsArray[hearts].visibility = View.INVISIBLE
            }

            // 3. Logic: Check Game Over
            if (hearts == 0) {
                isGameEnded = true
            }
        }
    }

}
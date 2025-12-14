package com.dev.nereya.ui_game_project.utils

data class AsteroidState(var rowStart: Int, var colIndex: Int) {
    val currentPosition: Int
        get() = rowStart + colIndex

    fun moveForward() {
        colIndex++

        if (colIndex > 4) {
            colIndex = -1
            rowStart = (0..2).random() * 5
        }
    }
}

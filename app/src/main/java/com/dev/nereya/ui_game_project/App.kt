package com.dev.nereya.ui_game_project

import android.app.Application
import com.dev.nereya.ui_game_project.utils.SignalManager

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SignalManager.init(this)
    }
}
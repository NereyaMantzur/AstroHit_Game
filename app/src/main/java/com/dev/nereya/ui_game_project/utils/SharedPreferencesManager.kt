package com.dev.nereya.ui_game_project.utils

import android.content.Context
import androidx.core.content.edit


class SharedPreferencesManager private constructor(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(
        Constants.SPKeys.DATA_FILE,
        Context.MODE_PRIVATE
    )

    companion object{
        @Volatile
        private var instance: SharedPreferencesManager? = null

        fun init(context: Context) : SharedPreferencesManager{
            return instance ?: synchronized(this){
                instance ?: SharedPreferencesManager(context).also { instance = it }
            }
        }

        fun getInstance(): SharedPreferencesManager {
            return instance ?: throw IllegalStateException(
                "SharedPreferencesManagerV3 must be initialized by calling init(context) before use."
            )

        }
    }

    fun putString( key: String, value: String){
        sharedPreferences.edit {
            putString(key, value)
        }
    }

    fun getString( key: String, defaultValue: String): String{
        return sharedPreferences
            .getString(
                key,
                defaultValue
            ) ?: defaultValue
    }
}
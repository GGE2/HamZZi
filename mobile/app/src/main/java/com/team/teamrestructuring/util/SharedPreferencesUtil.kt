package com.team.teamrestructuring.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferencesUtil(context:Context) {
    private val prefs:SharedPreferences = context.getSharedPreferences("walk_counter",Context.MODE_PRIVATE)

    fun getPedometer(key:String,defVaule:Int):Int{
        return prefs.getInt(key,defVaule)
    }
    fun setPedometer(key:String,value:Int){
        prefs.edit().putInt(key,value).apply()
    }

}
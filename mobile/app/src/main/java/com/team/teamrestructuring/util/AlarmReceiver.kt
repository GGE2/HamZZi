package com.team.teamrestructuring.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.team.teamrestructuring.view.activities.HomeActivity

class AlarmReceiver:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.extras?.get("code")==HomeActivity.REQUEST_CODE){
            Log.d("지훈", "onReceive: ")
            ApplicationClass.sharedPreferencesUtil.setPedometer("walk_data",HomeActivity.current_counter)
        }
    }
}
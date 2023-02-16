package com.team.teamrestructuring.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.team.teamrestructuring.service.StepService
import com.team.teamrestructuring.view.activities.HomeActivity

class AlarmReceiver:BroadcastReceiver(){
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.extras?.get("code")==HomeActivity.REQUEST_CODE){
            Log.d("지훈", "onReceive: ")
            var data = intent?.extras?.get("data") as Int
            ApplicationClass.sharedPreferencesUtil.setPedometer("walk_counter",data)
        }
    }
}
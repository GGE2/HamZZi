package com.team.teamrestructuring.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ContentInfoCompat.Flags
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.Flag
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.team.teamrestructuring.R
import com.team.teamrestructuring.view.activities.HomeActivity

private const val TAG = "FcmService_지훈"
class FcmService : FirebaseMessagingService() {

    /**
     * 새로운 토큰이 생성될 때 마다 콜백 호출
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: $token")
        //새로운 토큰 수신 시 서버 이동 추후 개발
    }
    /**
     * Foreground,Background 모두 처리
     */
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onMessageReceived(message: RemoteMessage) { //Foreground 있는 경우
        super.onMessageReceived(message)
        var messageTitle = ""
        var messageContent = ""
        if (message.notification != null) {
            messageTitle = message.notification!!.title!!
            messageContent = message.notification!!.title!!
            var intent = Intent(applicationContext, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra("data", message.notification!!.body!!)
            }
            startActivity(intent)
        } else {  // background 에 있을경우 혹은 foreground에 있을경우 두 경우 모두
            var data = message.data
            messageTitle = data.get("title")!!
            messageContent = data.get("body")!!
            var intent = Intent(applicationContext, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP
                putExtra("data", data.get("body")!!)
            }
            startActivity(intent)
        }
        val mainIntent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val mainPendingIntent: PendingIntent
        = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_IMMUTABLE)
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder1 = NotificationCompat.Builder(this, HomeActivity.channel_id)
            .setSmallIcon(R.drawable.all_title)
            .setContentTitle(messageTitle)
            .setContentText(messageContent)
            .setAutoCancel(true)
            .setSound(defaultSound)
            .setContentIntent(mainPendingIntent)

        NotificationManagerCompat.from(this).apply {
            notify(101, builder1.build())
        }
    }
}



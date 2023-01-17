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
import androidx.core.view.ContentInfoCompat.Flags
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.Flag
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.team.teamrestructuring.R
import com.team.teamrestructuring.view.activities.HomeActivity

private const val TAG = "FcmService_지훈"
class FcmService : FirebaseMessagingService(){

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
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val from = message.from!!
        val title = message.notification?.title
        val body = message.notification?.body
        val requestId = Integer.parseInt(message.data["requestId"])
        sendNotification(title!!,body!!,requestId)
    }

    //Notification 설정
    @RequiresApi(Build.VERSION_CODES.S)
    private fun sendNotification(title:String, text:String, requestId:Int){
        val intent = Intent(applicationContext,HomeActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)

        val channelId = getString(R.string.notification_channel_id)
        val channelName = getString(R.string.default_notification_channel_name)
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this,channelId)
            .setAutoCancel(true)
            .setSound(defaultSound)
            .setContentText(text)
            .setContentTitle(title)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.all_title)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(0,notificationBuilder.build())
        }

    }

}
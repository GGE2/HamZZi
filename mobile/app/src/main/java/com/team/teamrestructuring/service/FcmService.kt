package com.team.teamrestructuring.service

import android.app.Notification
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ContentInfoCompat.Flags
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage
import com.team.teamrestructuring.R
import com.team.teamrestructuring.view.activities.HomeActivity


private const val TAG = "FcmService_지훈"
class FcmService : FirebaseMessagingService() {

    companion object{
        private const val CHANNEL_NAME = "Push Notification"
        private const val CHANNEL_ID = "Channel Id"
        private const val CHANNEL_DESCRIPTION = "Push Notification 채널"
    }

    /**
     * 새로운 토큰이 생성될 때 마다 콜백 호출
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: $token")
        //새로운 토큰 수신 시 서버 이동 추후 개발
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.data.get("title")
        val message = message.data.get("message")
        sendNotification(title,message)

    }
    private fun sendNotification(
        title:String?,
        message:String?
    ){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Oreo(26)이상 버전에는 channel 필요
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = CHANNEL_DESCRIPTION
            notificationManager.createNotificationChannel(channel)
        }
        NotificationManagerCompat.from(this)
            .notify((System.currentTimeMillis()/100).toInt(),createNotification(title,message))
    }
    private fun createNotification(
        title:String?,
        message:String?
    ):Notification{
        Log.d(TAG, "createNotification: ${title},${message}")
        val intent = Intent(this,HomeActivity::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(this,
            (System.currentTimeMillis()/100).toInt(),intent,PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.all_title)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        return notificationBuilder.build()
    }
}




package com.team.teamrestructuring.service

import android.app.Notification
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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

    /*
     * Foreground,Background 모두 처리
     */
    @SuppressLint("SuspiciousIndentation")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        
        remoteMessage.takeIf { it.data.isNotEmpty() }?.apply {
            Log.d(TAG, "onMessageReceived: ${this.data}")
        }
        val notificationManager = NotificationManagerCompat.from(
            applicationContext
        )
        val CHANNEL_ID = HomeActivity.channel_id
        val CHANNEL_NAME = "ham_test"
        var builder: NotificationCompat.Builder? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        } else {
            builder = NotificationCompat.Builder(applicationContext)
        }

        val title: String = remoteMessage.getNotification()!!.getTitle()!!
        val body: String = remoteMessage.getNotification()!!.getBody()!!

        builder!!.setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(com.team.teamrestructuring.R.drawable.people)
        Log.d(TAG, "onMessageReceived: ${title} and ${body}")

        val notification: Notification = builder.build()
        notificationManager.notify(1, notification)
    }
}




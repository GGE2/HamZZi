package com.team.teamrestructuring.service

<<<<<<< Updated upstream
import android.app.Notification
=======
import android.annotation.SuppressLint
>>>>>>> Stashed changes
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

    /**
<<<<<<< Updated upstream
     * Foreground,Background 모두 처리*/

    /*@SuppressLint("SuspiciousIndentation")
=======
     * Foreground,Background 모두 처리
     */
    @SuppressLint("SuspiciousIndentation")
>>>>>>> Stashed changes
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onMessageReceived(message: RemoteMessage) { //Foreground 있는 경우
        super.onMessageReceived(message)
        var messageTitle = ""
        var messageContent = ""
        Log.d(TAG, "onMessageReceived: noti1")
        if (message.notification != null) {
            messageTitle = message.notification!!.title!!
            messageContent = message.notification!!.title!!
            var intent = Intent(applicationContext, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra("data", message.notification!!.body!!)
                putExtra("clicknoti","QuestFragment")
            }
            Log.d(TAG, "onMessageReceived: noti1")
            startActivity(intent)
        } else {  // background 에 있을경우 혹은 foreground에 있을경우 두 경우 모두
            var data = message.data
            messageTitle = data.get("title")!!
            messageContent = data.get("body")!!
            var intent = Intent(applicationContext, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP
                putExtra("data", data.get("body")!!)
                putExtra("clicknoti","QuestFragment")
            }
            Log.d(TAG, "onMessageReceived: noti2")
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
    }*/

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




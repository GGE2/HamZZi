package com.team.teamrestructuring.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.team.teamrestructuring.util.AlarmReceiver
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.view.activities.HomeActivity
import java.util.*


private const val TAG = "StepService_지훈"
class StepService : Service(),SensorEventListener{

    private var myBinder:MyBinder = MyBinder()

    var check_user = ApplicationClass.sharedPreferencesUtil.getPedometer("walk_counter",-1)

    class MyBinder:Binder(){
        fun getService():StepService{
            return StepService()
        }
    }
    companion object{
        var mStepCounter:Int = 0
    }
    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor
    private lateinit var alarmManager: AlarmManager

    override fun onCreate() {
        super.onCreate()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if(sensor!=null){
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME)
        }
        if(check_user==-1){
            ApplicationClass.sharedPreferencesUtil.setPedometer("walk_counter", mStepCounter)
        }

    }

/*    fun setCallback(callback: StepCallback){
        this.callback = callback
    }*/

    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onSensorChanged(event: SensorEvent?) {
        Log.d(TAG, "onSensorChanged: ${mStepCounter}")
        if(event!!.sensor.type == Sensor.TYPE_STEP_COUNTER){
            if(mStepCounter==0) {
                Log.d(TAG, "onSensorChanged: ${mStepCounter}")
                mStepCounter  = event.values[0].toInt()
                setAlarmManager()
            }else{
                mStepCounter += event.values[0].toInt()
            }
            /*if(callback!=null)
                callback.onStepCallback(mStepCounter)*/
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, point: Int) {
        Log.d(TAG, "onAccuracyChanged: ")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if(sensor!=null){
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME)
        }
        setAlarmManager()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        unRegistManager()
        /*if(callback!=null)
            callback.unBindService()*/
        return super.onUnbind(intent)
    }

    fun unRegistManager(){
        try{
            sensorManager.unregisterListener(this)
        }catch(e:Exception){
            e.printStackTrace()
        }
    }



    @RequiresApi(Build.VERSION_CODES.M)
     fun setAlarmManager(){
        Log.d(TAG, "setAlarmManager: 알람 등록")
        val intent = Intent(this, AlarmReceiver::class.java).apply {
            putExtra("code", HomeActivity.REQUEST_CODE)
            putExtra("data",mStepCounter)
            setAction("com.team.register")
        }
        val pendingIntent = PendingIntent.getBroadcast(this,
            HomeActivity.REQUEST_CODE,intent,
            PendingIntent.FLAG_IMMUTABLE)

        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY,10)
        cal.set(Calendar.MINUTE,30)
        cal.set(Calendar.SECOND,59)
        cal.set(Calendar.MILLISECOND,999)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,cal.timeInMillis,pendingIntent)
    }

}
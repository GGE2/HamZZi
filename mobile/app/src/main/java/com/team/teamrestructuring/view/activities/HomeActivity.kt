package com.team.teamrestructuring.view.activities

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.ActivityHomeBinding
import com.team.teamrestructuring.dto.*
import com.team.teamrestructuring.service.HomeService
import com.team.teamrestructuring.service.LoginService
import com.team.teamrestructuring.service.PetService
import com.team.teamrestructuring.util.AlarmReceiver
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.util.CreatePetDialog
import com.team.teamrestructuring.view.adapters.ViewPagerAdapter
import com.team.teamrestructuring.view.fragments.HomeFragment
import com.team.teamrestructuring.view.fragments.QuestFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLDecoder
import java.util.Calendar


private const val TAG = "HomeActivity_지훈"
class HomeActivity : AppCompatActivity(),SensorEventListener,BottomNavigationView.OnNavigationItemSelectedListener,CreatePetDialog.CreatePetDialogInterface{

    companion object{
        const val channel_id = "team_channel"
        const val REQUEST_CODE = 1001
        var current_counter = 0
    }
    private lateinit var auth:FirebaseAuth
    private lateinit var binding : ActivityHomeBinding
    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor
    private lateinit var alarmManager: AlarmManager

    private val frameLayout:FrameLayout by lazy{
        binding.framelayoutMainFrame
    }
    private val bottomNavigation:BottomNavigationView by lazy{
        binding.bottomnavigationHomeNav
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        alarmManager = getSystemService(android.content.Context.ALARM_SERVICE) as AlarmManager
        init()
        Log.d(TAG, "walk: ${ApplicationClass.sharedPreferencesUtil.getPedometer("walk_data",0)}")
        Log.d(TAG, "onCreate: ${ApplicationClass.currentUser}")
    }

@RequiresApi(Build.VERSION_CODES.M)
private fun requirePermission(){
    if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACTIVITY_RECOGNITION)== PackageManager.PERMISSION_DENIED)
        requestPermissions(arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),0)
}


    /**
     * HomeActivity 초기화
     */

    @RequiresApi(Build.VERSION_CODES.O)
    private fun init(){
        initFirebase()
        setViewPager()
        setFullScreen()
        requirePermission()
        setAlarmManager()
        getPetInfo()
    }


    private fun getPetInfo(){
        val service = ApplicationClass.retrofit.create(PetService::class.java)
            .getPetInfo(ApplicationClass.currentUser.userProfile.nickname).enqueue(object:Callback<PetData>{
                override fun onResponse(call: Call<PetData>, response: Response<PetData>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()!!}")
                        ApplicationClass.petData = response.body()!!
                        HomeFragment.exp = response.body()!!.pet.exp
                    }
                }
                override fun onFailure(call: Call<PetData>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })


    }



    override fun onYesButtonClick() {
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "onRequestPermissionsResult: ")
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    }

    /**
     * viewPager adapter 연결
     */
    private fun setViewPager(){
        binding.viewpagerMainPager.adapter = ViewPagerAdapter(this)
        binding.viewpagerMainPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomnavigationHomeNav.menu.getItem(position).isChecked = true
            }
        })
        binding.bottomnavigationHomeNav.setOnNavigationItemSelectedListener(this)
    }


    override fun onStart() {
        super.onStart()
        if(sensor!=null){
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME)
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun setAlarmManager(){
        Log.d(TAG, "setAlarmManager: 알람 등록")
        val intent = Intent(this,AlarmReceiver::class.java).apply {
            putExtra("code", REQUEST_CODE)
            setAction("com.team.register")
        }
        val pendingIntent = PendingIntent.getBroadcast(this@HomeActivity, REQUEST_CODE,intent,PendingIntent.FLAG_IMMUTABLE)

        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY,23)
        cal.set(Calendar.MINUTE,59)
        cal.set(Calendar.SECOND,59)
        cal.set(Calendar.MILLISECOND,999)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,cal.timeInMillis,pendingIntent)
    }

    /**
     * 상태표시줄 , 하단 네비게이션 없애기기
    */
     fun setFullScreen(){
        //Android 11(R) 대응
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
            supportActionBar?.hide()
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            if(controller!=null){
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }else{ //R버전 이하 대응
            supportActionBar?.hide()
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }


    override fun onNewIntent(intent: Intent?) {
        try{
            Log.d(TAG, "onNewIntent: ")
            binding.viewpagerMainPager.currentItem = 2
        }catch(e:Exception){}
        super.onNewIntent(intent)
    }
    private fun initFirebase(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener {task->
            if(task.isSuccessful){
                ApplicationClass.currentUser.fcmToken = task.result
                val currentUser = ApplicationClass.currentUser
                val fcmUser:User = User(currentUser.email,currentUser.uid,currentUser.fcmToken)
                sendToServerFcmData(fcmUser)

            }
        }
    }

    /**
     * Home화면을 default로 한 bottomNavigation 설정
     * setOnNavigationItemSelectedListener is Deprecated
     * setOnItemSelectedListener 사용
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
            R.id.menu_home ->{
                binding.viewpagerMainPager.currentItem = 0
                return true
            }
            R.id.menu_todo ->{
                binding.viewpagerMainPager.currentItem = 1
                return true
            }
            R.id.menu_quest ->{
                binding.viewpagerMainPager.currentItem = 2
                return true
            }
            R.id.my_page ->{
                binding.viewpagerMainPager.currentItem = 3
                return true
            }
            else -> return false
        }

    }




    /**
     * 유저 FCM 토큰 서버에 전송
     */
    private fun sendToServerFcmData(user: User){
        val service = ApplicationClass.retrofit.create(HomeService::class.java)
            .insertFCM(user).enqueue(object:Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "유저 토큰 서버 전송 완료")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type==Sensor.TYPE_STEP_COUNTER){
            current_counter = event.values[0].toInt()
            Log.d(TAG, "onSensorChanged: ${current_counter}")
        }
    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d(TAG, "onAccuracyChanged: ")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
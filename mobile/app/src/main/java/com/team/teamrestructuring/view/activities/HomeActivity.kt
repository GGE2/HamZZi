package com.team.teamrestructuring.view.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.ActivityHomeBinding
import com.team.teamrestructuring.view.fragments.GuildFragment
import com.team.teamrestructuring.view.fragments.HomeFragment
import com.team.teamrestructuring.view.fragments.MyPageFragment
import com.team.teamrestructuring.view.fragments.TodoFragment


private const val TAG = "HomeActivity_지훈"
class HomeActivity : AppCompatActivity() {

    companion object{
        const val channel_id = "team_channel"
    }
    private lateinit var auth:FirebaseAuth
    private lateinit var binding : ActivityHomeBinding
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
        init()
    }

    /**
     * HomeActivity 초기화
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun init(){
        getFCM()
        setBottomNavigation()
    }

    /**
     * Notification 수신을 위한 채널 추가
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(id:String, name:String){
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id,name,importance)

        val notificationManager : NotificationManager
                = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    /**
     * FCM 토큰 수신
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFCM(){
        Log.d("token TAG", "getFCM: ")
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener{
            if(!it.isSuccessful){
                Log.d("token TAG", "FCM 토큰 얻기에 실패하였습니다",it.exception)
                return@OnCompleteListener
            }
            //token 정보 남기기
            Log.d("token TAG", "token 정보 : ${it.result?:"token is null"}")
        })
        createNotificationChannel(channel_id,"team9")
    }
    /**
     * Home화면을 default로 한 bottomNavigation 설정
     * setOnNavigationItemSelectedListener is Deprecated
     * setOnItemSelectedListener 사용
     */
    private fun setBottomNavigation(){
        Log.d(TAG, "setBottomNavigation: ")
        supportFragmentManager.beginTransaction().add(frameLayout.id, HomeFragment()).commit()
        bottomNavigation.setOnItemSelectedListener { item->
            replaceFragment(
            when(item.itemId){
                R.id.menu_home -> HomeFragment()
                R.id.menu_todo -> TodoFragment()
                R.id.menu_guild -> GuildFragment()
                else -> MyPageFragment()
                }
            )
            true
        }
    }

    /**
     * fragment 화면간 이동
     */
    private fun replaceFragment(fragment : Fragment){
        Log.d(TAG, "replaceFragment: ")
        supportFragmentManager.beginTransaction().replace(frameLayout.id,fragment).commit()
    }


 }
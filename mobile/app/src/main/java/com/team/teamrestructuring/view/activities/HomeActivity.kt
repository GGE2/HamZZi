package com.team.teamrestructuring.view.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.ActivityHomeBinding
import com.team.teamrestructuring.view.fragments.GuildFragment
import com.team.teamrestructuring.view.fragments.HomeFragment
import com.team.teamrestructuring.view.fragments.MyPageFragment
import com.team.teamrestructuring.view.fragments.TodoFragment


private const val TAG = "HomeActivity_지훈"
class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private val frameLayout:FrameLayout by lazy{
        binding.framelayoutMainFrame
    }
    private val bottomNavigation:BottomNavigationView by lazy{
        binding.bottomnavigationHomeNav
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    /**
     * HomeActivity 초기화
     */
    private fun init(){
        setBottomNavigation()
    }

    /**
     * Notification 수신을 위한 채널 추가
     */
    private fun createNotificationChannel(id:String,name:String){
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id,name,importance)

    }
    private fun getFCM(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener{
            if(!it.isSuccessful){
                Log.d(TAG, "FCM 토큰 얻기에 실패하였습니다",it.exception)
                return@OnCompleteListener
            }
            //token 정보 남기기
            Log.d(TAG, "token 정보 : ${it.result}")
        })
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
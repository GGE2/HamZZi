package com.team.teamrestructuring.view.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.ActivityHomeBinding
import com.team.teamrestructuring.dto.User
import com.team.teamrestructuring.service.HomeService
import com.team.teamrestructuring.service.LoginService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.util.CreatePetDialog
import com.team.teamrestructuring.view.adapters.ViewPagerAdapter
import com.team.teamrestructuring.view.fragments.QuestFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLDecoder


private const val TAG = "HomeActivity_지훈"
class HomeActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener,CreatePetDialog.CreatePetDialogInterface{

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
        Log.d(TAG, "onCreate: ${ApplicationClass.currentUser}")
    }


    /**
     * HomeActivity 초기화
     */

    @RequiresApi(Build.VERSION_CODES.O)
    private fun init(){
        initFirebase()
        setViewPager()
        setFullScreen()
    }



    override fun onYesButtonClick() {
        finish()
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




}
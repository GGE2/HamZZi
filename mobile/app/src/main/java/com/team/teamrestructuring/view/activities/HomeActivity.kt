package com.team.teamrestructuring.view.activities

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.ActivityHomeBinding
import com.team.teamrestructuring.view.fragments.GuildFragment
import com.team.teamrestructuring.view.fragments.HomeFragment
import com.team.teamrestructuring.view.fragments.MyPageFragment
import com.team.teamrestructuring.view.fragments.TodoFragment

private val TAG = "HomeActivity"
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
     * Home화면을 default로 한 bottomNavigation 설정
     * setOnNavigationItemSelectedListener is Deprecated
     * setOnItemSelectedListener 사용
     */
    private fun setBottomNavigation(){
        Log.d(TAG, "setBottomNavigation: ")
        supportFragmentManager.beginTransaction().add(frameLayout.id,HomeFragment()).commit()
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
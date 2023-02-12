package com.team.teamrestructuring.view.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import com.team.teamrestructuring.R
import com.team.teamrestructuring.util.CreateUserNickNameDialog

class CreateUserNickNameActivity : AppCompatActivity() ,CreateUserNickNameDialog.CreateUserNickNameDialog{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_nick_name)
        showRegisterUserNickNameDialog()
        setFullScreen()

    }


    private fun showRegisterUserNickNameDialog(){
        val dialog1 = CreateUserNickNameDialog(this@CreateUserNickNameActivity)
        dialog1.isCancelable = false
        dialog1.show(this.supportFragmentManager,"CreateUserNicknameDialog")
    }

    /**
     * 상태표시줄 , 하단 네비게이션 없애기기
     */
    fun setFullScreen(){
        //Android 11(R) 대응
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.R){
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

    override fun onClick() {
        val intent = Intent(this,CreatePetActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        overridePendingTransition(R.anim.horizon_exit,R.anim.none)
    }

}
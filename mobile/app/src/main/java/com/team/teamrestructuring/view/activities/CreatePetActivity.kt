package com.team.teamrestructuring.view.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.ActivityCreatePetBinding
import com.team.teamrestructuring.util.CreatePetDialog
import com.team.teamrestructuring.util.CreateUserNickNameDialog

private const val TAG = "CreatePet_지훈"
class CreatePetActivity : AppCompatActivity(),CreatePetDialog.CreatePetDialogInterface{
    lateinit var binding:ActivityCreatePetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showRegisterPetDialog()
        setFullScreen()
    }

    override fun onYesButtonClick() {
        val intent = Intent(this,HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        overridePendingTransition(R.anim.horizon_exit,R.anim.none)
    }



    private fun showRegisterPetDialog(){
        val dialog2 = CreatePetDialog(this@CreatePetActivity)
        dialog2.isCancelable = false
        dialog2.show(this.supportFragmentManager,"CreatePetDialog")
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


}
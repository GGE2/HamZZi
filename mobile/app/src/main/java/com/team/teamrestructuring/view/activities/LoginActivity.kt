package com.team.teamrestructuring.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var te:TextView = binding.googleSignIn.getChildAt(0) as TextView
        te.text = "구글 계정으로 로그인"
    }
    // 카카오 로그인

}
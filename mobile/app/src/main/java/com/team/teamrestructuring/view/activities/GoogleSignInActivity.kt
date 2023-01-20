package com.team.teamrestructuring.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.team.teamrestructuring.databinding.ActivityGoogleSignInBinding
import com.team.teamrestructuring.view.activities.LoginActivity.Companion.EXTRA_NAME



class GoogleSignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGoogleSignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.textDisplayName.text = intent.getStringExtra(EXTRA_NAME)
//        binding.logout.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
////            Firebase.auth.signOut()
//
//            val intent = Intent(applicationContext, LoginActivity::class.java)
//            startActivity(intent)
//        }
    }
}
package com.team.teamrestructuring.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        val signup = findViewById<Button>(R.id.signup)
        signup.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailtext)
            val password = findViewById<EditText>(R.id.passwortext)
            val passwordCheck = findViewById<EditText>(R.id.passwordcomform)

            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) {task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(this, "회원 가입 성공", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(baseContext, "회원가입에 실패하였습니다",
                            Toast.LENGTH_LONG).show()
                    }

                }
        }


    }

}
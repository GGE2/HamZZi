package com.team.teamrestructuring.view.activities

import android.content.ContentValues
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.kakao.auth.AuthType
import com.kakao.auth.Session
import com.kakao.sdk.common.util.Utility
import com.team.teamrestructuring.databinding.ActivityLoginBinding
import com.team.teamrestructuring.dto.User
import com.team.teamrestructuring.dto.UserInfo
import com.team.teamrestructuring.service.KakaoService
import com.team.teamrestructuring.service.LoginService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.util.SessionCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.*


private const val TAG: String = "LoginActivity_지훈"
class LoginActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callback: SessionCallback
    private lateinit var auth: FirebaseAuth

    private lateinit var binding:ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        callback = SessionCallback(this)
        val typeFacefont:Typeface = Typeface.createFromAsset(assets,"font/maple.ttf")
        var googleSign: TextView = binding.googleSignIn.getChildAt(0) as TextView
        googleSign.text = "구글 계정으로 로그인"
        googleSign.textSize = 16f
        googleSign.typeface = typeFacefont
        //google sign in
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("616408032515-pdlld4de1ettr6i8rd00qh8ofosf77ge.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.googleSignIn.setOnClickListener {
            signIn()
        }
        binding.kakaoSignIn.setOnClickListener {
            kakaoLoginStart()
        }


    }
    private fun kakaoLoginStart(){
        /*val keyHash = Utility.getKeyHash(this) // keyHash 발급
        //Log.d(TAG, "KEY_HASH : $keyHash")
        Log.d(TAG, "kakaoLoginStart: ")*/
        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().open(AuthType.KAKAO_TALK, this)
    }




    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_Sign_in)
    }

    /**
     * 메인 액티비티로 이동하는 Intent 생성
     *
     */
    private fun loginandhome() {
        val intent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        Log.d(TAG, "loginandhome: ")
        startActivity(intent)
    }

    /**
     * 구글 로그인 세션 생성
     * Success = firebase 유저 생성
     * Fail = 토스트 생성
  성  */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)){
            Log.i(TAG, "Session get current session")
            return
        }
        if (requestCode == RC_Sign_in) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle" + account.id)
                firebaseAuthWithGoogle(account.idToken.toString())
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed")
            }
        }
    }

    /**
     * 파이어베이스 사용자 등록
     * Success : 유저 등록후 메인 페이지로 이동
     * Fail : 실패 토스트 생성
     */
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "signInWithCredential:success")
                    val uid = FirebaseAuth.getInstance().currentUser!!.uid
                    val email = FirebaseAuth.getInstance().currentUser!!.email
                    val user:User = User(email!!,uid)
                    sendToServerUserData(user)
                    loginandhome()
                } else {
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }
    private fun sendToServerUserData(user:User){
        val service = ApplicationClass.retrofit.create(LoginService::class.java)
            .insertUser(user).enqueue(object:Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.d(TAG, "onResponse: ${response.body()}")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })


    }
    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    /**
     * 카카오톡 로그인 세션 종료시 CustomFirebaseToken 발급 요청
     *
     */

    open fun getFirebaseJwt(code:String){
        val service = ApplicationClass.retrofit.create(LoginService::class.java)
            .selectKakaoUser(code).enqueue(object:Callback<Map<String,Any>>{
                override fun onResponse(
                    call: Call<Map<String, Any>>,
                    response: Response<Map<String, Any>>
                ) {
                    val datas = response.body()
                    val auth = FirebaseAuth.getInstance()
                    val firebaseToken = datas?.get("customToken") as String
                    val userInfo : Map<String,Any> = datas?.get("userInfo") as Map<String,Any>
                    val uid = "kakao"+userInfo.get("id")
                    val email = userInfo.get("email") as String
                    val user:User = User(email,uid)
                    FirebaseAuth.getInstance().signInWithCustomToken(firebaseToken)
                    sendToServerUserData(user)
                    loginandhome()
                }

                override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }
    /*open fun getFirebaseJwt(code : String){
        val client_state = UUID.randomUUID().toString()
        val service = ApplicationClass.retrofit.create(KakaoService::class.java)
            .getFirebaseToken(code
            )
            .enqueue(object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: retrofit2.Response<String>
                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()}")
                        val datas = response.body()!!
                        val auth = FirebaseAuth.getInstance()
                        Log.d(TAG, "onSuccess: ${}")
                        auth.signInWithCustomToken(firebaseToken)
                        sendToServerUserData()
                        loginandhome()
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }
            })
    }*/

    companion object{
        const val RC_Sign_in = 1001
        const val EXTRA_NAME = "EXTRA NAME"
    }

}
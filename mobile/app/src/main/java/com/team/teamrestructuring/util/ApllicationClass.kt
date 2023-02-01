
package com.team.teamrestructuring.util

import android.app.Application
import android.util.Log
import com.google.gson.GsonBuilder
import com.kakao.auth.KakaoSDK
import com.kakao.sdk.common.KakaoSdk
import com.team.teamrestructuring.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG="ApplicationClass_지훈"
class ApplicationClass : Application(){


    //url must be end with "/"
    val BOARD_URL   = "http://3.35.88.23:8080/"

    companion object {
        // 전역변수 문법을 통해 Retrofit 인스턴스를 앱 실행 시 1번만 생성하여 사용 (싱글톤 객체)
        lateinit var retrofit: Retrofit
        //lateinit var sharedPreferencesUtil:SharedPreferencesUtil
        var instance : ApplicationClass? = null

    }

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this,"98207802012408560593bd7763f3bedd")
        instance = this
        if(KakaoSDK.getAdapter()==null){
            KakaoSDK.init(KakaoSDKAdapter(getApplicationClassContext()))
        }


        val gson = GsonBuilder().setLenient().create()
        // 앱이 처음 생성되는 순간, retrofit 인스턴스를 생성
        retrofit = Retrofit.Builder()
            .baseUrl(BOARD_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        //KakaoSdk.init(this,getString(R.string.NATIVE_APP_KEY))
        //sharedPreferencesUtil = SharedPreferencesUtil(applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }
    fun getApplicationClassContext() : ApplicationClass{
        //Log.d(TAG, "getApplicationClassContext: ${instance}")
        checkNotNull(instance){
            "This Application does not inherit com.example.App"
        }
        return instance!!
    }



}
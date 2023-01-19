package com.team.teamrestructuring.util

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationClass : Application(){
    //url must be end with "/"
    val BOARD_URL   = "http://3.35.88.23:8080/"

    companion object {
        // 전역변수 문법을 통해 Retrofit 인스턴스를 앱 실행 시 1번만 생성하여 사용 (싱글톤 객체)
        lateinit var retrofit: Retrofit
        //lateinit var sharedPreferencesUtil:SharedPreferencesUtil
    }

    override fun onCreate() {
        super.onCreate()

        // 앱이 처음 생성되는 순간, retrofit 인스턴스를 생성
        retrofit = Retrofit.Builder()
            .baseUrl(BOARD_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //sharedPreferencesUtil = SharedPreferencesUtil(applicationContext)
    }

}
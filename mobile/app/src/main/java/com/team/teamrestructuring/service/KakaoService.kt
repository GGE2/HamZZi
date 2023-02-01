package com.team.teamrestructuring.service

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoService {

    @GET("api/kakao/app")
    fun getFirebaseToken(
        @Query("code") code : String
    ): Call<String>

}
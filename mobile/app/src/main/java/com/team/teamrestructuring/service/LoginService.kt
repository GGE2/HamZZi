package com.team.teamrestructuring.service

import com.team.teamrestructuring.dto.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @POST("api/user/register")
    fun insertUser(@Body user: User): Call<String>
    @GET("api/kakao/app")
    fun selectKakaoUser(@Query("code")code:String):Call<Map<String,Any>>
}
package com.team.teamrestructuring.service

import com.team.teamrestructuring.dto.User
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @POST("api/user/register")
    fun insertUser(@Body user: User): Call<String>
    @GET("api/kakao/app")
    fun selectKakaoUser(@Query("code")code:String):Call<Map<String,Any>>
    @GET("api/user/uid/{email}")
    fun isSignUser(@Path("email") email : String) : Call<Boolean>
    @GET("api/user/info/{uid}")
    fun getUserInfo(@Path("uid") uid:String) : Call<User>

}
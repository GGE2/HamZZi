package com.team.teamrestructuring.service

import com.team.teamrestructuring.dto.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Query

interface HomeService {

    @PUT("api/user/fcm")
    fun insertFCM(@Body user: User) : Call<String>
    @PUT("api/user/nickname")
    fun createNickName(@Query("nickname") nickname:String,@Query("email")email:String) : Call<String>


}
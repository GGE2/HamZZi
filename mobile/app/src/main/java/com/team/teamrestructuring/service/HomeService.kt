package com.team.teamrestructuring.service

import com.team.teamrestructuring.dto.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface HomeService {

    @PUT("api/user/fcm")
    fun insertFCM(@Body user: User) : Call<String>
    @PUT("api/user/nickname")
    suspend fun createNickName(@Query("nickname") nickname:String,@Query("email")email:String) : Response<String>



}
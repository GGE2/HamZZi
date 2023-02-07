package com.team.teamrestructuring.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QuestService {

    @POST("api/quest/user/{nickname}")
    fun createQuestUser(@Path("nickname") nickname:String):Call<String>
}
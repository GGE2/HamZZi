package com.team.teamrestructuring.service

import com.team.teamrestructuring.dto.DailyQuest
import com.team.teamrestructuring.dto.WeeklyQuest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface QuestService {

    @POST("api/quest/user/{nickname}")
    suspend fun createQuestUser(@Path("nickname") nickname:String): Response<String>
    @GET("api/quest/daily/{nickname}")
    fun getQuestList(@Path("nickname") nickname:String):Call<List<DailyQuest>>
    @GET("api/quest/weekly/{nickname}")
    fun getWeeklyQuestList(@Path("nickname") nickname:String):Call<List<WeeklyQuest>>
    @PUT("api/quest/time")
    fun setFinishTime(@Query("nickname") nickname:String,@Query("finish_time") time:Int):Call<String>
    @PUT("api/quest/daily")
    fun updateQuestResult(@Query("nickname") nickname: String,
                          @Query("questDaily_id") qUid:Int,
                          @Query("quest_id") qId:Int):Call<String>
    @PUT("api/quest/weekly")
    fun updateWeeklyQuestResult(
        @Query("nickname") nickname:String,
        @Query("questWeekly_id") qUid:Int,
        @Query("quest_id") qId:Int):Call<String>


}
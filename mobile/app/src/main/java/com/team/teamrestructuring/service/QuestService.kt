package com.team.teamrestructuring.service

import com.team.teamrestructuring.dto.DailyQuest
import com.team.teamrestructuring.dto.WeeklyQuest
import retrofit2.Call
import retrofit2.http.*

interface QuestService {

    @POST("api/quest/user/{nickname}")
    fun createQuestUser(@Path("nickname") nickname:String):Call<String>
    @GET("api/quest/daily/{nickname}")
    fun getQuestList(@Path("nickname") nickname:String):Call<List<DailyQuest>>
    @GET("api/quest/weekly/{nickname}")
    fun getWeeklyQuestList(@Path("nickname") nickname:String):Call<List<WeeklyQuest>>
    @PUT("api/quest/time")
    fun setFinishTime(@Query("nickname") nickname:String,@Query("finish_time") time:Int):Call<String>
    @PUT("api/quest/check")
    fun updateQuestResult(@Query("nickname") nickname: String,
                          @Query("questUser_id") qUid:Int,
                          @Query("quest_id") qId:Int):Call<String>
}
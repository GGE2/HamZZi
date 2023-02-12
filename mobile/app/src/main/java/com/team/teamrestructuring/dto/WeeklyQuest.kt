package com.team.teamrestructuring.dto

import com.google.gson.annotations.SerializedName

data class WeeklyQuest(
    @SerializedName("questWeekly_id")
    var questWeekly_id:Long,
    var nickname:String,
    var ischeck : Boolean = false,
    @SerializedName("quest")
    var quest : Quest
){
    override fun toString(): String {
        return "WeeklyQuest(questWeekly_id=$questWeekly_id, nickname='$nickname', ischeck=$ischeck, quest=$quest)"
    }
}

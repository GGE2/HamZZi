package com.team.teamrestructuring.dto

import com.google.gson.annotations.SerializedName

data class DailyQuest(

    @SerializedName("questUser_id")
    var q_user_id:Long,
    var nickname:String,
    var ischeck : Boolean = false,
    @SerializedName("quest")
    var quest : Quest
) {
    override fun toString(): String {
        return "DailyQuest(q_user_id=$q_user_id, nickname='$nickname', ischeck=$ischeck, quest=$quest)"
    }
}
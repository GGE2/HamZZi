package com.team.teamrestructuring.dto

import com.google.gson.annotations.SerializedName

data class DailyQuest(

    @SerializedName("questDaily_id")
    var questDaily_id:Long,
    var nickname:String,
    var ischeck : Boolean = false,
    @SerializedName("quest")
    var quest : Quest
) {
    override fun toString(): String {
        return "DailyQuest(questDaily_id=$questDaily_id, nickname='$nickname', ischeck=$ischeck, quest=$quest)"
    }
}
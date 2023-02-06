package com.team.teamrestructuring.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserProfile(
<<<<<<< HEAD
    @Expose
    @SerializedName("nickname")
    var user_nickname:String="",

    @Expose
    @SerializedName("point")
    var point:Int,

    @Expose
    @SerializedName("rest_point")
    var rest_point:Int
){




    override fun toString(): String {
        return "UserProfile(user_nickname='$user_nickname', point=$point, rest_point=$rest_point)"
    }
}
=======
    @Expose @SerializedName("user_nickname") var user_nickname : String,
    @Expose @SerializedName("point") var point : Int,
    @Expose @SerializedName("rest_point") var rest_point : Int
)
>>>>>>> b24c2d732eb5a3882f8dab596779e91ab70e0eeb

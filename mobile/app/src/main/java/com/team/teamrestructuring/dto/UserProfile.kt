package com.team.teamrestructuring.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserProfile(
    @Expose
    @SerializedName("nickname")
    var nickname:String,

    @Expose
    @SerializedName("point")
    var point:Int,

    @Expose
    @SerializedName("rest_point")
    var rest_point:Int,

    @Expose
    @SerializedName("finish_datetime")
    var time : Int = -1,

    @Expose
    @SerializedName("location")
    var location:String="",

    @Expose
    @SerializedName("latitude")
    var latitude:Double = 0.0,

    @Expose
    @SerializedName("longitude")
    var longitude:Double = 0.0


){


    override fun toString(): String {
        return "UserProfile(nickname='$nickname', point=$point, rest_point=$rest_point, time=$time, location='$location', latitude=$latitude, longitude=$longitude)"
    }
}
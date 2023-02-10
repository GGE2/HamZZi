package com.team.teamrestructuring.dto

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id")var uid:String,
    @SerializedName("email")var email:String,
    @SerializedName("user_nickname")var nickname:String){
    override fun toString(): String {
        return "UserInfo(uid='$uid', email='$email', user_nickname='$nickname')"
    }
}

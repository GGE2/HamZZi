package com.team.teamrestructuring.dto

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id")var uid:String,
    @SerializedName("email")var email:String,
    @SerializedName("nickname")var nickname:String){
    override fun toString(): String {
        return "UserInfo(uid='$uid', email='$email', nickname='$nickname')"
    }
}

package com.team.teamrestructuring.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose @SerializedName("email")var email : String,
    @Expose @SerializedName("uid")var uid : String) {

    @Expose
    @SerializedName("login_token")
    var loginToKen:String =""

    @Expose
    @SerializedName("user_nickname")
    var userNickname:String=""

    constructor(email:String,uid:String,loginToKen:String,userNickname:String) : this(email,uid) {
        this.loginToKen = loginToKen
        this.userNickname = userNickname
    }

    override fun toString(): String {
        return "User(email='$email', uid='$uid', login_token='$loginToKen', user_nickname='$userNickname')"
    }


}

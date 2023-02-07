package com.team.teamrestructuring.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(@Expose @SerializedName("email") var email : String) {

    @Expose
    @SerializedName("uid")
    var uid : String=""

    @Expose
    @SerializedName("fcmToken")
    var fcmToken:String =""

    @Expose
    @SerializedName("userProfile")
    var userProfile:UserProfile = UserProfile("",0,3,)

    constructor(email:String,uid:String,fcmToken: String,userProfile: UserProfile):this(email){
        this.uid = uid
        this.fcmToken = fcmToken
        this.userProfile = userProfile
    }


    constructor(email:String,uid:String,fcmToken:String) : this(email){
        this.fcmToken = fcmToken
        this.uid = uid
    }
    constructor(email:String,uid:String) : this(email){
        this.uid = uid
    }

    override fun toString(): String {
        return "User(email='$email', uid='$uid', fcmToken='$fcmToken', userProfile=$userProfile)"
    }


}

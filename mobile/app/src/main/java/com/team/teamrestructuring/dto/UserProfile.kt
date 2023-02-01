package com.team.teamrestructuring.dto

data class UserProfile(
    var user_nickname:String,
    var point:Int,
    var rest_point:Int
){
    override fun toString(): String {
        return "UserProfile(user_nickname='$user_nickname', point=$point, rest_point=$rest_point)"
    }
}

package com.team.teamrestructuring.dto

data class User(
    var email : String,
    var uid : String,
    var userProfile:UserProfile
){
    override fun toString(): String {
        return "User(email='$email', uid='$uid', userProfile=$userProfile)"
    }
}

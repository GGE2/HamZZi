package com.team.teamrestructuring.dto

import com.google.gson.annotations.SerializedName

data class CreatePet(

    @SerializedName("name")
    var name : String,
    @SerializedName("user_nickname")
    var user_nickname:String
){
    override fun toString(): String {
        return "CreatePet(name='$name', user_nickname='$user_nickname')"
    }
}

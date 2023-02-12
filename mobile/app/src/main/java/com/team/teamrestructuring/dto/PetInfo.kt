package com.team.teamrestructuring.dto

import com.google.gson.annotations.SerializedName

data class PetInfo(
    @SerializedName("petInfo_id")
    var petInfo_id:Long,

    @SerializedName("pet")
    var pet:Pet,

    @SerializedName("type")
    var type:Int
){
    override fun toString(): String {
        return "PetInfo(petInfo_id=$petInfo_id, pet=$pet, type=$type)"
    }
}

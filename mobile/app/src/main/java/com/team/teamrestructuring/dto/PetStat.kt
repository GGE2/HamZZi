package com.team.teamrestructuring.dto

import com.google.gson.annotations.SerializedName

data class PetStat(
    @SerializedName("petStat_id")
    var petStat_id:Long,

    @SerializedName("pet")
    var pet:Pet,

    @SerializedName("physical")
    var physical:Int,

    @SerializedName("artistic")
    var artistic:Int,

    @SerializedName("intelligent")
    var intelligent:Int,

    @SerializedName("inactive")
    var inactive:Int,

    @SerializedName("energetic")
    var energetic:Int,

    @SerializedName("etc")
    var etc:Int
){
    override fun toString(): String {
        return "PetStat(petStat_id=$petStat_id, pet=$pet, physical=$physical, artistic=$artistic, intelligent=$intelligent, inactive=$inactive, energetic=$energetic, etc=$etc)"
    }
}

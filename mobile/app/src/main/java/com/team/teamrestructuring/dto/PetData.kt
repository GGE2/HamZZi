package com.team.teamrestructuring.dto

import com.google.gson.annotations.SerializedName

data class PetData(
    @SerializedName("pet")
    var pet:Pet,
    @SerializedName("petInfo")
    var petInfo:PetInfo,
    @SerializedName("petStat")
    var petStat:PetStat
){
    override fun toString(): String {
        return "PetData(pet=$pet, petInfo=$petInfo, petStat=$petStat)"
    }
}

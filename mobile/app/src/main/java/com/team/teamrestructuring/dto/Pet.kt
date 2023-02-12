package com.team.teamrestructuring.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Pet(
    @SerializedName("pet_id")
    var pet_id:Long,

    @SerializedName("nickname")
    var nickname:String,

    @SerializedName("pet_name")
    var pet_name:String,

    @SerializedName("exp")
    var exp :Int,

    @SerializedName("level")
    var level:Int,

    @SerializedName("create_date")
    var create_date:String,

    @SerializedName("graduate_date")
    var graduate_date:String?,

    @SerializedName("_graduate")
    var _graduate:Boolean
){
    override fun toString(): String {
        return "Pet(pet_id=$pet_id, nickname='$nickname', pet_name='$pet_name', exp=$exp, level=$level, create_date=$create_date, graduate_date=$graduate_date, _graduate=$_graduate)"
    }
}

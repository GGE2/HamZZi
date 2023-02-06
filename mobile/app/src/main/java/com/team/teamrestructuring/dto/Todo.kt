package com.team.teamrestructuring.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Todo(
    @Expose @SerializedName("content") var content:String,
    @Expose @SerializedName("datetime")var dateTime:String,
    @Expose @SerializedName("todo_id") var todo_id : Long= -1
    ){


    @Expose
    @SerializedName("ischeck")
    var is_check : Boolean = false

    @Expose
    @SerializedName("userProfile")
    var userProfile:UserProfile? = null

    constructor(todo_id:Long , content:String, dateTime:String,is_check:Boolean,userProfile: UserProfile):this(content, dateTime, todo_id){
        this.is_check = is_check
        this.userProfile = userProfile
    }
}

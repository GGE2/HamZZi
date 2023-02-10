package com.team.teamrestructuring.dto

import android.provider.ContactsContract.CommonDataKinds.Nickname
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.team.teamrestructuring.util.ApplicationClass

data class Todo(
    @Expose @SerializedName("content") var content:String,
    @Expose @SerializedName("datetime")var dateTime:String,
    @Expose @SerializedName("user_nickname") var userNickname:String? = ApplicationClass.currentUser.userProfile?.nickname,
    @Expose @SerializedName("todo_id") var todo_id : Long?= null
){
    @Expose
    @SerializedName("ischeck")
    var is_check : Boolean = false


    constructor(todo_id:Long , content:String, dateTime:String,is_check:Boolean,userNickname: String):this(content, dateTime, userNickname){
        this.todo_id = todo_id
        this.is_check = is_check
    }

    // 그 외 요청시
    constructor(todo_id:Long , content:String, dateTime:String,is_check:Boolean):this(todo_id, content, dateTime, is_check, ""){}
}



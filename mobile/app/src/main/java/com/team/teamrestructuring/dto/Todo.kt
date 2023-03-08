package com.team.teamrestructuring.dto

import android.provider.ContactsContract.CommonDataKinds.Nickname
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.team.teamrestructuring.util.ApplicationClass

data class Todo(
    @Expose @SerializedName("content") var content:String,
    @Expose @SerializedName("datetime")var dateTime:String,
    @Expose @SerializedName("user_nickname") var user_nickname : String
){
    @Expose
    @SerializedName("todo_id")
    var todo_id:Long = -1

    @Expose
    @SerializedName("ischeck")
    var ischeck:Boolean = false

    constructor(todo_id:Long , content:String, dateTime:String,ischeck:Boolean,user_nickname: String):this(content, dateTime, user_nickname){
            this.todo_id = todo_id
            this.ischeck = ischeck
    }

    override fun toString(): String {
        return "Todo(content='$content', dateTime='$dateTime', user_nickname='$user_nickname', todo_id=$todo_id, ischeck=$ischeck)"
    }


}



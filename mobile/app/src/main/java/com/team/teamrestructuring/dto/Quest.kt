package com.team.teamrestructuring.dto

data class Quest(
    var quest_id:Long,
    var content:String,
    var point:Int,
    var type:String?

){
    override fun toString(): String {
        return "Quest(quest_id=$quest_id, content='$content', point=$point, type=$type)"
    }
}

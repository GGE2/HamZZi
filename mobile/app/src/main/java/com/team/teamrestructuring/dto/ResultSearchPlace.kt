package com.team.teamrestructuring.dto

data class ResultSearchPlace(
    var documents: List<Place>
){
    override fun toString(): String {
        return "ResultSearchPlace(documents=$documents)"
    }
}

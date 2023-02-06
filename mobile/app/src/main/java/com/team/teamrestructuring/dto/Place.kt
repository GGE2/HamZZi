package com.team.teamrestructuring.dto

data class Place(
    var place_name:String,
    var address_name:String,
    var road_address_name:String,
    var x : String,
    var y : String
){
    override fun toString(): String {
        return "Place(place_name='$place_name', address_name='$address_name', road_address_name='$road_address_name', x='$x', y='$y')"
    }
}

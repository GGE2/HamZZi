package com.team.teamrestructuring.service

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Path

interface PlaceService {

    @PUT("api/quest/location/")
    fun registerPlace(@Path("latitude") latitude:Double,@Path("longitude") longitude:Double) : Call<String>

}
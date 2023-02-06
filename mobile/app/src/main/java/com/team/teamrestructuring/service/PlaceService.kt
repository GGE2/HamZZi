package com.team.teamrestructuring.service

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceService {

    @PUT("api/quest/location")
    fun registerPlace(@Query("nickname") nickname:String, @Query("latitude") latitude:Double,
                      @Query("longitude") longitude:Double ) : Call<String>

}
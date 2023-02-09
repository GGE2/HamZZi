package com.team.teamrestructuring.service

import com.team.teamrestructuring.dto.Place
import com.team.teamrestructuring.dto.ResultSearchPlace
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query

interface MyPageService {

    @GET("v2/local/search/keyword.json")
    fun getSearchKeyword(
        @Header("Authorization") key : String,
        @Query("query") query : String
    ): Call<ResultSearchPlace>
}
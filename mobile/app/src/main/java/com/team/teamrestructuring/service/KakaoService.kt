package com.team.teamrestructuring.service

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoService {
    //@GET("oauth/authorize?client_id=98207802012408560593bd7763f3bedd&redirect_uri=http://3.35.88.23:8001/kakao/sign_in&response_type=code")
    @GET("kakao_login/app")
    fun getFirebaseToken(
        @Query("code") code : String
     /*   @Query("client_id") client_id: String,
        @Query("redirect_uri") redirect_uri : String,
        @Query("response_type") response_type : String,
        @Query("state") state : String*/
    ): Call<String>
    @GET("test")
    fun test(
    ): Call<String>

}
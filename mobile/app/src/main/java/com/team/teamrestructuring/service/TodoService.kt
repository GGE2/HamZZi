package com.team.teamrestructuring.service

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
// 각  API 설명 확인 해서 다시 해야함
//
interface TodoService {
    // 투두 가져옴
    @GET("api/todo/{id}")
    fun gettodo(id:String): Call<MutableList<String>>

    // 투두리스트 생성
    @POST()
    fun createtodo()

    // 투두 클리어
    @PUT("api/todo/{id}")
    fun checktodo(id: String): Call<String>

    // 투두 수정
    fun modify(id: String): Call<String>


    // 투두 삭제
    @DELETE("api/todo/{id}")
    fun deletetodo(id: String): Call<String>

}
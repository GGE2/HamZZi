package com.team.teamrestructuring.service

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface TodoService {
    // 투두 가져옴
    @GET()
    fun gettodo()

    // 투두리스트 생성
    @POST()
    fun createtodo()

    // 투두 클리어
    @PUT("/todo")
    fun checktodo()

    // 투두 삭제
    @DELETE()
    fun deletetodo()
}
package com.team.teamrestructuring.service

import com.team.teamrestructuring.dto.CreatePet
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PetService {

    @POST("api/pet")
    fun createPet(@Body pet:CreatePet) : Call<String>

}
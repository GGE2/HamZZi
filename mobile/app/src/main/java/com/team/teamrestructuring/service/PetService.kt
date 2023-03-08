package com.team.teamrestructuring.service

import com.team.teamrestructuring.dto.CreatePet
import com.team.teamrestructuring.dto.PetData
import com.team.teamrestructuring.dto.UpdatePetStat
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface PetService {

    @POST("api/pet")
    fun createPet(@Body pet:CreatePet) : Call<String>

    @GET("api/pet/{nickname}")
    suspend fun getPetInfo(@Path("nickname") nickname:String) : Response<PetData>

    @PUT("api/pet/exp")
    fun petUpdatePetExp(@Query("pet_id") pet_id:Long , @Query("exp") exp : Int,@Query("nickname") nickname: String) : Call<String>

    @PUT("api/pet/stat")
    fun updatePetStat(@Query("nickname") nickname: String , @Body data:UpdatePetStat) : Call<String>
}
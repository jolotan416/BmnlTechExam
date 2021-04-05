package com.studytest.bmnltechexam.httpclient

import com.studytest.bmnltechexam.data.developer.Developer
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getDevelopers(): List<Developer>

    @POST("users")
    suspend fun addDeveloper(@Body developer: Developer): Developer

    @PATCH("users/{id}")
    suspend fun editDeveloper(@Query("id") id: String, @Body developer: Developer): Developer
}
package com.studytest.bmnltechexam.httpclient

import com.studytest.bmnltechexam.data.developer.Developer
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getDevelopers(): List<Developer>
}
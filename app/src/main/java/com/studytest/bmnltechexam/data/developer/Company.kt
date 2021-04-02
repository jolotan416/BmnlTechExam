package com.studytest.bmnltechexam.data.developer

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("name")
    val name: String
)
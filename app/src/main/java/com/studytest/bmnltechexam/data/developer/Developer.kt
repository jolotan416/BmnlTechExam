package com.studytest.bmnltechexam.data.developer

import com.google.gson.annotations.SerializedName

data class Developer(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("company")
    val company: Company
)

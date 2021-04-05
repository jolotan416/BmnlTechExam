package com.studytest.bmnltechexam.data.developer

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(
    @SerializedName("name")
    val name: String = ""
) : Parcelable
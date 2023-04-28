package com.demo.med.home.data.models


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DrugsResponse(
    @Expose @SerializedName("problems")
    val problems: List<Problem>
) : Parcelable
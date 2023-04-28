package com.demo.med.home.data.models


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Problem(
    @Expose @SerializedName("Asthma")
    val asthma: List<Asthma>,
    @Expose @SerializedName("Diabetes")
    val diabetes: List<Diabete>
) : Parcelable
package com.demo.med.home.data.models


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Diabete(
    @Expose @SerializedName("labs")
    val labs: List<Lab>,
    @Expose @SerializedName("medications")
    val medications: List<Medication>
) : Parcelable
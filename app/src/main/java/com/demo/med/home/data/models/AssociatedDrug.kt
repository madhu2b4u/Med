package com.demo.med.home.data.models


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AssociatedDrug(
    @Expose @SerializedName("dose")
    val dose: String,
    @Expose @SerializedName("name")
    val name: String,
    @Expose @SerializedName("strength")
    val strength: String
) : Parcelable
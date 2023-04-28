package com.demo.med.home.data.models


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MedicationsClasse(
    @Expose @SerializedName("className")
    val className: List<ClassName>,
    @Expose @SerializedName("className2")
    val className2: List<ClassName>
) : Parcelable
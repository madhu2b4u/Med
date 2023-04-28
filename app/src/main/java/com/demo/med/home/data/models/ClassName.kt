package com.demo.med.home.data.models


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClassName(
    @Expose @SerializedName("associatedDrug")
    val associatedDrug: List<AssociatedDrug>,
    @Expose @SerializedName("associatedDrug#2")
    val associatedDrug2: List<AssociatedDrug>
): Parcelable
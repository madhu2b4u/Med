package com.demo.med.home.data.models


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Medication(
    @Expose @SerializedName("medicationsClasses")
    val medicationsClasses: List<MedicationsClasse>
): Parcelable
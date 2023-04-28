package com.demo.med

import com.demo.med.database.entites.HealthData
import com.demo.med.home.data.models.AssociatedDrug
import com.demo.med.home.data.models.Asthma
import com.demo.med.home.data.models.ClassName
import com.demo.med.home.data.models.Diabete
import com.demo.med.home.data.models.DrugsResponse
import com.demo.med.home.data.models.Lab
import com.demo.med.home.data.models.Medication
import com.demo.med.home.data.models.MedicationsClasse
import com.demo.med.home.data.models.Problem

class TestUtils {

    companion object {

        val asprin = AssociatedDrug("asprin", "", "500 mg")
        val somethingElse = AssociatedDrug("somethingElse", "", "500 mg")
        val anotherDrug = AssociatedDrug("anotherDrug", "", "250 mg")
        val yetAnotherDrug = AssociatedDrug("yetAnotherDrug", "", "100 mg")


        val className = ClassName(listOf(asprin), listOf(somethingElse))
        val className2 = ClassName(listOf(anotherDrug), listOf(yetAnotherDrug))

        val medicationClass1 = MedicationsClasse(listOf(className), listOf(className2))
        val medicationClass2 = MedicationsClasse(listOf(className), listOf(className2))

        val medication1 = Medication(listOf(medicationClass1))
        val medication2 = Medication(listOf(medicationClass2))

        val lab1 = Lab("missing_value")
        val lab2 = Lab("some_value")

        val diabetes1 = Diabete( listOf(lab1), listOf(medication1))
        val diabetes2 = Diabete( listOf(lab1, lab2), listOf(medication2))

        val asthma1 = Asthma()

        val problem1 = Problem(listOf(asthma1), listOf(diabetes1))
        val problem2 = Problem(listOf(), listOf(diabetes2))

        val response = DrugsResponse(listOf(problem1, problem2))

        val healthDataList = mutableListOf<HealthData>(
            HealthData("Diabete", "", "asprin", "500 mg"),
            HealthData("Diabete", "", "somethingElse", "500 mg"),
            HealthData("Diabete", "", "anotherDrug", "250 mg"),
            HealthData("Diabete", "", "yetAnotherDrug", "100 mg"),
            HealthData("Diabete", "", "asprin", "500 mg"),
            HealthData("Diabete", "", "somethingElse", "500 mg"),
            HealthData("Diabete", "", "anotherDrug", "250 mg"),
            HealthData("Diabete", "", "yetAnotherDrug", "100 mg"),
        )


    }



}
package com.demo.med.home.data.repository

import androidx.lifecycle.LiveData
import com.demo.med.common.Result
import com.demo.med.home.presentation.viewmodel.HealthData


interface HomeRepository {
    suspend fun drugsRequest(isLoaded: Boolean): LiveData<Result<MutableList<HealthData>>>

}
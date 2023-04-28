package com.demo.med.home.domain

import androidx.lifecycle.LiveData
import com.demo.med.common.Result
import com.demo.med.home.presentation.viewmodel.HealthData

interface HomeUseCase {
    suspend fun drugsRequest(isLoaded: Boolean): LiveData<Result<MutableList<HealthData>>>

}
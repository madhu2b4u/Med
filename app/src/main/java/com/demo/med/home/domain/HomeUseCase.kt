package com.demo.med.home.domain

import androidx.lifecycle.LiveData
import com.demo.med.common.Result
import com.demo.med.home.data.models.DrugsResponse

interface HomeUseCase {
    suspend fun drugsRequest(): LiveData<Result<DrugsResponse>>

}
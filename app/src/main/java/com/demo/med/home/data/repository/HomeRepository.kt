package com.demo.med.home.data.repository

import androidx.lifecycle.LiveData
import com.demo.med.common.Result
import com.demo.med.home.data.models.DrugsResponse


interface HomeRepository {

    suspend fun drugsRequest(): LiveData<Result<DrugsResponse>>

}
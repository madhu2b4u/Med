package com.demo.med.home.data.remote.services

import com.demo.med.home.data.models.DrugsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    // id to fetch from the mock apis
    @GET("6f75cb9-25d2-48cd-8ef0-298ba6691e96")
    fun requestDrugsAsync(): Deferred<Response<DrugsResponse>>
}
package com.demo.med.home.data.repository


import androidx.lifecycle.liveData
import com.demo.med.common.Result
import com.demo.med.home.data.remote.source.HomeRemoteDataSource
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource
) : HomeRepository {
    override suspend fun drugsRequest() = liveData {
        emit(Result.loading())
        try {
            val response = remoteDataSource.drugsRequest()
            emit(Result.success(response))

        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: "", null))
        }
    }
}
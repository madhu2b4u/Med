package com.demo.med.home.data.repository


import com.demo.med.home.data.remote.source.HomeRemoteDataSource
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource
) : HomeRepository {



}
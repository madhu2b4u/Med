package com.demo.med.home.presentation.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.demo.med.common.BaseViewModel
import com.demo.med.common.Result
import com.demo.med.common.SingleLiveEvent
import com.demo.med.common.Status
import com.demo.med.common.support.AppCoroutineDispatcherProvider
import com.demo.med.common.support.AppCoroutineDispatchers
import com.demo.med.database.entites.HealthData
import com.demo.med.home.domain.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mHomeUseCase: HomeUseCase
) : BaseViewModel(), LifecycleObserver {

    val data: LiveData<MutableList<HealthData>> get() = _data
    private val _data = MediatorLiveData<MutableList<HealthData>>()

    val showLoader: LiveData<Unit> get() = _showLoader
    private val _showLoader = SingleLiveEvent<Unit>()

    val healthData = MutableLiveData<HealthData>()
    fun sendHealthData(health: HealthData) {
        healthData.value = health
    }


    private val dispatcher: AppCoroutineDispatchers = AppCoroutineDispatcherProvider.dispatcher()

    fun getProblemsList(isLoaded: Boolean) {
        viewModelScope.launch {
            withContext(dispatcher.main()) {
                _data.addSource(mHomeUseCase.drugsRequest(isLoaded)) {
                    handleDrugsResponse(it)
                }
            }
        }
    }

    private fun handleDrugsResponse(it: Result<MutableList<HealthData>>) {
        when (it.status) {
            Status.LOADING -> {
                _showLoader.call()
            }

            Status.ERROR -> {
                //handle if there is an error
            }

            Status.SUCCESS -> it.data?.let { it1 -> handleProblems(it.data) }
        }
    }

    private fun handleProblems(data: MutableList<HealthData>) {
        _data.value = data
    }
}
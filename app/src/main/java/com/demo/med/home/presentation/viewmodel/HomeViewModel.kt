package com.demo.med.home.presentation.viewmodel

import androidx.lifecycle.LifecycleObserver
import com.demo.med.common.BaseViewModel
import com.demo.med.home.domain.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mHomeUseCase: HomeUseCase
) : BaseViewModel(), LifecycleObserver {



}
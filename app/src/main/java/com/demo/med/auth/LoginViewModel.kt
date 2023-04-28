package com.demo.med.auth

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.med.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel(), LifecycleObserver {

    private val _loginSuccess = MutableLiveData<String>()
    val loginSuccess: LiveData<String>
        get() = _loginSuccess

    fun login(username: String, password: String) {
        //handle login logic
        _loginSuccess.value = username
    }
}
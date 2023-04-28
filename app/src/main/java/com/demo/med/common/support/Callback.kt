package com.demo.med.common.support

interface Callback<T> {
    fun onComplete(result: T)
    fun onException(e: Exception?)
}

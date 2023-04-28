package com.demo.med.common.extensions

import com.demo.med.common.support.BaseNetworkException
import com.demo.med.common.support.is4xx
import com.demo.med.common.support.isForbidden
import com.demo.med.common.support.isUnauthorized
import retrofit2.HttpException

fun Throwable.isNetworkUnAuthorizeOrForbidden(): Boolean {
    return isNetworkUnauthorized() || isNetworkForbidden()
}

fun Throwable.isNetworkUnauthorized(): Boolean {
    if (this is BaseNetworkException && this.isUnauthorized()) {
        return true
    }
    if (this is HttpException && this.isUnauthorized()) {
        return true
    }
    return false
}

fun Throwable.isNetworkForbidden(): Boolean {
    if (this is BaseNetworkException && this.isForbidden()) {
        return true
    }
    if (this is HttpException && this.isForbidden()) {
        return true
    }
    return false
}

fun Throwable.isNetwork4xx(): Boolean {
    if (this is BaseNetworkException && this.is4xx()) {
        return true
    }
    if (this is HttpException && this.is4xx()) {
        return true
    }
    return false
}

fun Int.isUnauthorizedCode() = this == 401
fun Int.isForbiddenCode() = this == 403
fun Int.is4xx() = this in 400..499
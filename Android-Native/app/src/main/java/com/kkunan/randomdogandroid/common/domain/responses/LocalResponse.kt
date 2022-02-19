package com.kkunan.randomdogandroid.common.domain.responses

sealed class LocalResponse<T> {
    data class SUCCESS<T>(val value: T) : LocalResponse<T>()
    data class FAILURE<T>(val error: String): LocalResponse<T>()
}
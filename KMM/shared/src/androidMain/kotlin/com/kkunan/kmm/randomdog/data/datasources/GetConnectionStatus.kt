package com.kkunan.kmm.randomdog.data.datasources

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.kkunan.kmm.randomdog.di.ContextStorage


@SuppressLint("MissingPermission")
actual fun getConnectionStatus(): Boolean {
    val context = ContextStorage.context
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

    return cm?.activeNetwork?.let {
        true
    } ?: false
}
package com.kkunan.kmm.randomdog.data.datasources

import cocoapods.Reachability.Reachability

actual fun getConnectionStatus(): Boolean {
    val reachability = Reachability()
    return reachability.isReachable()
}
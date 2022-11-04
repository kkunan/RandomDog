package com.kkunan.kmm.randomdog.data.datasources

import kotlinx.coroutines.flow.StateFlow

interface NetworkStatusDatasource {
    val isConnected: Boolean
}

class NetworkStatusDatasourceImpl(private val connectionStatus: () -> Boolean) : NetworkStatusDatasource {
    override val isConnected: Boolean get() = connectionStatus()
}

expect fun getConnectionStatus(): Boolean
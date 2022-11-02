package com.kkunan.kmm.randomdog.data.datasources

import dev.tmapps.konnection.Konnection

interface NetworkStatusDatasource {
    val isConnected: Boolean
}

class NetworkStatusDatasourceImpl(private val connection: Konnection) : NetworkStatusDatasource {
    override val isConnected: Boolean get() = connection.isConnected()
}
package com.kkunan.kmm.randomdog.data.datasources

import dev.tmapps.konnection.Konnection
import io.mockk.every
import io.mockk.mockk
import kotlin.random.Random
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NetworkStatusDatasourceImplTest {
    private lateinit var konnection: Konnection
    private lateinit var datasource: NetworkStatusDatasourceImpl

    @BeforeTest
    fun setUp() {
        konnection = mockk()
        datasource = NetworkStatusDatasourceImpl(konnection)
    }

    @Test
    fun `isConnected should return value from konnection isConnected`() {
        // arrange
        val isConnected = Random.nextBoolean()
        every { konnection.isConnected() } returns isConnected

        // act
        val result = datasource.isConnected

        // assert
        assertEquals(result, isConnected)
    }
}
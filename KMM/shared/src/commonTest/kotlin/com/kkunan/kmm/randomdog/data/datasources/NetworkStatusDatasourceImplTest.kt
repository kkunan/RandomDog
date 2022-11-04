package com.kkunan.kmm.randomdog.data.datasources

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NetworkStatusDatasourceImplTest {
    private lateinit var connectivity: () -> Boolean
    private lateinit var datasource: NetworkStatusDatasourceImpl

    @BeforeTest
    fun setUp() {
        connectivity = mockk()
        datasource = NetworkStatusDatasourceImpl(connectivity)
    }

    @Test
    fun `isConnected should return value from konnection isConnected`() {
        // arrange
        val isConnected = Random.nextBoolean()
        every { connectivity() } returns isConnected

        // act
        val result = datasource.isConnected

        // assert
        assertEquals(result, isConnected)
    }
}
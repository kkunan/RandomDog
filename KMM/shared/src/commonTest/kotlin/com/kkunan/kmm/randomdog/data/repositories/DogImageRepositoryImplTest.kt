@file:OptIn(ExperimentalCoroutinesApi::class)

package com.kkunan.kmm.randomdog.data.repositories

import com.kkunan.kmm.randomdog.data.datasources.DogImageNetworkDatasource
import com.kkunan.kmm.randomdog.data.datasources.NetworkStatusDatasource
import com.kkunan.kmm.randomdog.data.models.RandomImagesRequest
import com.kkunan.kmm.randomdog.data.models.RandomImagesResponse
import com.kkunan.kmm.randomdog.domain.entities.DogImage
import com.kkunan.kmm.randomdog.getRandomString
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.random.Random
import kotlin.test.*

class DogImageRepositoryImplTest {

    lateinit var networkStatusDatasource: NetworkStatusDatasource
    lateinit var dogImageNetworkDatasource: DogImageNetworkDatasource
    lateinit var repository: DogImageRepositoryImpl

    @BeforeTest
    fun setUp() {
        networkStatusDatasource = mockk {
            every { isConnected } returns true
        }

        dogImageNetworkDatasource = mockk(relaxed = true)

        repository = DogImageRepositoryImpl(
            networkDatasource = networkStatusDatasource,
            dogImageNetworkDatasource = dogImageNetworkDatasource
        )
    }

    @Test
    fun `randomImage should return null if no internet connection`() {
        runTest {
            // arrange
            coEvery { networkStatusDatasource.isConnected } returns false

            // act
            val response = repository.randomImage(Random.nextInt())

            // assert
            assertNull(response)
            coVerify(exactly = 1) { networkStatusDatasource.isConnected }
            coVerify(exactly = 0) { dogImageNetworkDatasource.getRandomImages(any()) }
        }
    }

    @Test
    fun `randomImage should call datasource with correct request`() {
        runTest {
            // arrange
            val request = RandomImagesRequest(Random.nextInt())

            // act
            repository.randomImage(request.number)

            // assert
            coVerify(exactly = 1) { dogImageNetworkDatasource.getRandomImages(request) }
        }
    }

    @Test
    fun `randomImage should return null if datasource throw exception`() {
        runTest {
            // arrange
            coEvery { dogImageNetworkDatasource.getRandomImages(any()) } throws Exception()

            // act
            val response = repository.randomImage(Random.nextInt())

            // assert
            assertNull(response)
        }
    }

    @Test
    fun `randomImage should return empty if datasource result list empty`() {
        runTest {
            // arrange
            coEvery { dogImageNetworkDatasource.getRandomImages(any()) } returns RandomImagesResponse(
                imageUrls = emptyList()
            )

            // act
            val response = repository.randomImage(Random.nextInt())

            // assert
            assertTrue(response?.isEmpty() == true)
        }
    }

    @Test
    fun `randomImage should return null if datasource result list null`() {
        runTest {
            // arrange
            coEvery { dogImageNetworkDatasource.getRandomImages(any()) } returns RandomImagesResponse(
                imageUrls = null
            )

            // act
            val response = repository.randomImage(Random.nextInt())

            // assert
            assertNull(response)
        }
    }

    @Test
    fun `randomImage should map datasource response urls to DogImage with such urls`() {
        runTest {
            // arrange
            val expected = (1..Random.nextInt(30)).map {
                DogImage(imageUrl = getRandomString(10))
            }
            coEvery { dogImageNetworkDatasource.getRandomImages(any()) } returns RandomImagesResponse(
                imageUrls = expected.map { it.imageUrl }
            )

            // act
            val response = repository.randomImage(Random.nextInt())

            // assert
            assertEquals(response, expected)
        }
    }
}
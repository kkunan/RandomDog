package com.kkunan.randomdogandroid.features.randomimage.data.datasources

import com.kkunan.randomdogandroid.features.randomimage.data.apis.DogCeoApiInterface
import com.kkunan.randomdogandroid.features.randomimage.data.models.RandomDogImageResponseModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class RandomDogNetworkDatasourceImplTest {

    lateinit var datasourceImpl: RandomDogNetworkDatasourceImpl
    lateinit var mockApi: DogCeoApiInterface

    @Before
    fun setUp() {
        mockApi = mockk()
        datasourceImpl = RandomDogNetworkDatasourceImpl(mockApi)
    }

    @Test
    fun getRandomImage_should_pass_correct_parameter_to_api() {
        runTest {
            // arrange
            val number = Random.nextInt()
            coEvery { datasourceImpl.getRandomImage(any()) } coAnswers {
                mockk()
            }

            // act
            datasourceImpl.getRandomImage(number)

            // assert
            coVerify { mockApi.getRandomDogImage(number) }
        }
    }

    @Test
    fun getRandomImage_should_return_the_same_object_with_api(){
        runTest {
            // arrange
            val expected = RandomDogImageResponseModel(
                listOf("url1", "url2"), status = "testStatus"
            )
            coEvery { mockApi.getRandomDogImage(any()) } coAnswers {
                expected
            }

            // act
            val response = datasourceImpl.getRandomImage(3)

            // assert
            assertEquals(response, expected)
        }
    }

}
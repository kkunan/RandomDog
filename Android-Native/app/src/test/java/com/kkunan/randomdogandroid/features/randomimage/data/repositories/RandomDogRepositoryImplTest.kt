package com.kkunan.randomdogandroid.features.randomimage.data.repositories

import com.kkunan.randomdogandroid.common.domain.responses.LocalResponse
import com.kkunan.randomdogandroid.features.randomimage.data.datasources.RandomDogNetworkDatasource
import com.kkunan.randomdogandroid.features.randomimage.data.models.RandomDogImageResponseModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class RandomDogRepositoryImplTest {

    lateinit var repository: RandomDogRepositoryImpl
    lateinit var datasource: RandomDogNetworkDatasource

    @Before
    fun setUp() {
        datasource = mockk()
        repository = RandomDogRepositoryImpl(datasource)
    }

    @Test
    fun randomImage_should_pass_correct_parameters_to_datasource() {
        runTest {
            // arrange
            coEvery { datasource.getRandomImage(any()) } answers {
                RandomDogImageResponseModel(null, "failed")
            }
            val expected = Random.nextInt()

            // act
            repository.randomImage(expected)

            // assert
            coVerify { datasource.getRandomImage(expected) }
        }
    }

    @Test
    fun randomImage_should_return_failed_if_status_is_failed(){
        runTest {
            // arrange
            coEvery { datasource.getRandomImage(any()) } answers {
                RandomDogImageResponseModel(null, "failed")
            }

            // act
            val response = repository.randomImage(2)

            // assert
            if (response is LocalResponse.FAILURE){
                assertEquals(response.error, "Cannot get dog images :'(")
            } else fail()
        }
    }

    @Test
    fun randomImage_should_return_correct_urls_if_status_is_success(){
        runTest {
            // arrange
            val urls = listOf("url1", "url2")

            coEvery { datasource.getRandomImage(any()) } answers {
                RandomDogImageResponseModel(urls, "success")
            }

            // act
            val response = repository.randomImage(2)

            // assert
            if (response is LocalResponse.SUCCESS){
                assertEquals(response.value.map { it.url }, urls)
            } else fail()
        }
    }
}
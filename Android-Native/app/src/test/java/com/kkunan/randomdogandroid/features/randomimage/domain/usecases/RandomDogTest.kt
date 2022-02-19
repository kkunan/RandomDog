package com.kkunan.randomdogandroid.features.randomimage.domain.usecases

import com.kkunan.randomdogandroid.common.domain.responses.LocalResponse
import com.kkunan.randomdogandroid.features.randomimage.domain.entities.DogImage
import com.kkunan.randomdogandroid.features.randomimage.domain.repositories.RandomDogRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class RandomDogTest {

    private lateinit var randomDog: RandomDog
    private lateinit var repository: RandomDogRepository

    @Before
    fun setUp() {
        repository = mockk()
        randomDog = RandomDog(repository)
    }

    @Test
    fun randomImage_should_pass_correct_parameters_to_repository() {
        runTest {
            // arrange
            coEvery { repository.randomImage(any()) } answers {
                LocalResponse.SUCCESS(emptyList())
            }
            val expected = Random.nextInt()

            // act
            randomDog.randomImage(expected)

            // assert
            coVerify { repository.randomImage(expected) }
        }
    }

    @Test
    fun randomImage_should_return_success_if_repository_returns_success(){
        runTest {
            // arrange
            val dogs = listOf(DogImage("url"))
            coEvery { repository.randomImage(any()) } answers {
                LocalResponse.SUCCESS(dogs)
            }

            // act
            val response = randomDog.randomImage(2)

            // assert
            if (response is LocalResponse.SUCCESS){
                assertEquals(response.value, dogs)
            } else fail()
        }
    }

    @Test
    fun randomImage_should_return_failed_if_repository_return_failed(){
        runTest {
            // arrange
            val errorMessage = "test"
            coEvery { repository.randomImage(any()) } answers {
                LocalResponse.FAILURE(errorMessage)
            }

            // act
            val response = randomDog.randomImage(2)

            // assert
            if (response is LocalResponse.FAILURE){
                assertEquals(response.error, errorMessage)
            } else fail()
        }
    }

}
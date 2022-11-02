@file:OptIn(ExperimentalCoroutinesApi::class)

package com.kkunan.kmm.randomdog.domain.usecases

import com.kkunan.kmm.randomdog.domain.entities.DogImage
import com.kkunan.kmm.randomdog.domain.repositories.DogImageRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.*


internal class RandomDogImageTest {
    lateinit var useCase: RandomDogImage
    lateinit var repository: DogImageRepository

    @BeforeTest
    fun setUp() {
        repository = mockk(relaxed = true)
        useCase = RandomDogImage(repository)
    }

    @AfterTest
    fun tearDown() {

    }

    @Test
    fun `invoke should call repository randomImage with input 1`(){
        runTest {
            // arrange

            // act
            useCase()

            // assert
            coVerify(exactly = 1) { repository.randomImage(1) }
        }
    }

    @Test
    fun `invoke should return first image of repository result`(){
        runTest {
            // arrange
            val expected: DogImage = mockk()
            coEvery { repository.randomImage(any()) } returns listOf(expected)

            // act
            val response = useCase()

            // assert
            assertEquals(expected, response)
        }
    }

    @Test
    fun `invoke should return null if repository result is null`(){
        runTest {
            // arrange
            coEvery { repository.randomImage(any()) } returns null

            // act
            val response = useCase()

            // assert
            assertNull(response)
        }
    }

    @Test
    fun `invoke should return null if repository result is emptyList`(){
        runTest {
            // arrange
            coEvery { repository.randomImage(any()) } returns emptyList()

            // act
            val response = useCase()

            // assert
            assertNull(response)
        }
    }
}
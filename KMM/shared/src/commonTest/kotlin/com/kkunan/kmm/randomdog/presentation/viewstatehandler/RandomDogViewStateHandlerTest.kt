@file:OptIn(ExperimentalCoroutinesApi::class)

package com.kkunan.kmm.randomdog.presentation.viewstatehandler

import com.kkunan.kmm.randomdog.domain.entities.DogImage
import com.kkunan.kmm.randomdog.domain.usecases.RandomDogImage
import com.kkunan.kmm.randomdog.getRandomString
import com.kkunan.kmm.randomdog.presentation.uistates.RandomDogUiState
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class RandomDogViewStateHandlerTest {

    private lateinit var stateHandler: RandomDogViewStateHandler
    private lateinit var randomDogImage: RandomDogImage

    @BeforeTest
    fun setup() {
        randomDogImage = mockk(relaxed = true)
        stateHandler = RandomDogViewStateHandler(randomDogImage = randomDogImage)
    }

    @Test
    fun `stateHandler should initialize with default state`() {
        // arrange

        // act

        // assert
        assertEquals(RandomDogUiState(), stateHandler.uiState)
    }

    @Test
    fun `stateHandler should initialize with input state`() {
        // arrange
        val state = mockk<RandomDogUiState>()

        // act
        stateHandler = RandomDogViewStateHandler(
            initialState = state,
            randomDogImage = randomDogImage
        )

        // assert
        assertEquals(state, stateHandler.uiState)
    }

    @Test
    fun `randomNextImage should do nothing if current state isLoading`() {
        runTest {
            // arrange
            stateHandler = RandomDogViewStateHandler(
                initialState = RandomDogUiState(isLoading = true),
                randomDogImage = randomDogImage
            )

            val storedState = mutableListOf<RandomDogUiState>()

            // act
            stateHandler.randomNextImage {
                storedState.add(it)
            }

            // assert
            coVerify(exactly = 0) { randomDogImage() }
            assertEquals(emptyList(), storedState)
        }
    }

    @Test
    fun `randomNextImage should update isLoading true then call randomDogImage then update isLoading false`() {
        runTest {
            // arrange
            val expected = listOf<Any>(true, "randomDogImage()", false)
            val storedState = mutableListOf<Any>()
            coEvery { randomDogImage() } answers {
                storedState.add("randomDogImage()")
                mockk(relaxed = true)
            }

            // act
            stateHandler.randomNextImage {
                storedState.add(it.isLoading)
            }

            // assert
            assertEquals(expected, storedState)
        }
    }

    @Test
    fun `randomNextImage should update dogImage with the received url`(){
        runTest {
            // arrange
            val mockUrl = getRandomString(20)
            coEvery { randomDogImage() } returns mockk {
                every { imageUrl } returns mockUrl
            }
            var lastStateUrl: String? = null

            // act
            stateHandler.randomNextImage {
                lastStateUrl = it.dogImageUrl
            }

            // assert
            assertEquals(mockUrl, lastStateUrl)
        }
    }
}
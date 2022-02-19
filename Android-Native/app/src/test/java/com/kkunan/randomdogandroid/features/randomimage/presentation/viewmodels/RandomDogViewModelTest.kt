package com.kkunan.randomdogandroid.features.randomimage.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kkunan.randomdogandroid.common.domain.responses.LocalResponse
import com.kkunan.randomdogandroid.features.randomimage.domain.entities.DogImage
import com.kkunan.randomdogandroid.features.randomimage.domain.usecases.RandomDog
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomDogViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RandomDogViewModel
    private lateinit var randomDog: RandomDog
    private val testDispatcher = TestCoroutineDispatcher()

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        randomDog = mockk()
        viewModel = RandomDogViewModel(randomDog)
    }

    @Test
    fun isLoading_should_be_false_at_the_beginning() {
        assertFalse(viewModel.isLoading.value!!)
    }

    @Test
    fun dogImageUrl_should_be_null_at_the_beginning() {
        assertNull(viewModel.dogImageUrl.value)
    }

    // should update loading to true
    // fetch
    // loading to false
    // if success update dogImageUrl
    @Test
    fun randomNewImage_should_update_loading_to_true_if_called() {
        runTest {
            // arrange
            coEvery { randomDog.randomImage(any()) } coAnswers {
                LocalResponse.SUCCESS(emptyList())
            }
            val observer = mockk<Observer<Boolean>>()
            val slot = slot<Boolean>()
            val list = mutableListOf<Boolean>()
            every { observer.onChanged(capture(slot)) } answers {
                if (viewModel.dogImageUrl.value == null) {
                    list.add(slot.captured)
                }
            }
            viewModel.isLoading.observeForever(observer)

            // act
            viewModel.randomNewImage()

            // assert
            assertEquals(mutableListOf(false, true), list)
        }
    }

    @Test
    fun randomNewImage_should_pass_correct_parameters_to_randomDog(){
        runTest {
            // arrange
            coEvery { randomDog.randomImage() } answers {
                LocalResponse.SUCCESS(emptyList())
            }
            // act
            viewModel.randomNewImage()

            // assert
            coVerify { randomDog.randomImage(1) }
        }
    }

    @Test
    fun randomNewImage_should_update_isLoading_to_false_after_received_result(){
        runTest {
            // arrange
            coEvery { randomDog.randomImage() } answers {
                LocalResponse.SUCCESS(emptyList())
            }
            // act
            viewModel.randomNewImage()

            // assert
            assertFalse(viewModel.isLoading.value!!)
        }
    }

    @Test
    fun randomNewImage_should_update_dogImage_if_success(){
        runTest {
            // arrange
            val images = listOf(DogImage("url1"))
            coEvery { randomDog.randomImage() } answers {
                LocalResponse.SUCCESS(images)
            }
            // act
            viewModel.randomNewImage()

            // assert
            assertEquals(images, viewModel.dogImageUrl.value)
        }
    }
}
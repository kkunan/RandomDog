package com.kkunan.randomdogandroid.features.randomimage.presentation.views

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.MutableLiveData
import coil.Coil
import coil.EventListener
import coil.ImageLoader
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.request.SuccessResult
import com.kkunan.randomdogandroid.features.randomimage.domain.entities.DogImage
import com.kkunan.randomdogandroid.features.randomimage.presentation.viewmodels.RandomDogViewModel
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.MainScope
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import kotlin.random.Random

class RandomDogScreenKtTest {

    private lateinit var viewModel: RandomDogViewModel

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        viewModel = mockk(relaxed = true)
    }

    @Test
    fun randomDogScreen_should_have_clickable_text_random() {
        // arrange
        composeTestRule.setContent {
            RandomDogScreen(viewModel)
        }
        val randomMatcher = composeTestRule.onNodeWithText("RANDOM!")

        // act

        // assert
        randomMatcher.assertExists()
        randomMatcher.assertHasClickAction()
    }

    @Test
    fun randomDogScreen_should_call_viewModel_randomNextImage_if_click_on_random_text(){
        // arrange
        composeTestRule.setContent {
            RandomDogScreen(viewModel)
        }
        val randomMatcher = composeTestRule.onNodeWithText("RANDOM!")

        // act
        randomMatcher.performClick()

        // assert
        coVerify { viewModel.randomNewImage() }
    }

    @Test(timeout = 30_000)
    fun randomDogScreen_should_load_image_with_url_from_viewModel_using_coil(){
        // arrange
        val imageUrl = "https://developer.android.com/static/images/jetpack/compose/compose-testing-cheatsheet.png"
        every { viewModel.dogImageUrl } returns MutableLiveData(listOf(DogImage(imageUrl)))

        var successUrl: String? = null
        val countdownLatch = CountDownLatch(2)
        composeTestRule.setContent {
            Coil.setImageLoader(
                ImageLoader.Builder(LocalContext.current)
                    .eventListener(object : EventListener {
                        override fun onSuccess(
                            request: ImageRequest,
                            metadata: ImageResult.Metadata
                        ) {
                            successUrl = request.data.toString()
                            countdownLatch.countDown()
                        }

                        override fun onError(request: ImageRequest, throwable: Throwable) {
                            countdownLatch.countDown()
                        }
                    })
                    .build()
            )
            RandomDogScreen(viewModel)
        }

        // act

        // assert
        val image = composeTestRule.onNodeWithTag("currentDogImage")
        image.assertIsDisplayed()

        countdownLatch.await()
        assertEquals(imageUrl, successUrl)
    }

    @After
    fun tearDown() {

    }
}
package com.kkunan.randomdogandroid.features.randomimage.presentation.views

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.kkunan.randomdogandroid.features.randomimage.presentation.viewmodels.RandomDogViewModel
import io.mockk.mockk
import kotlinx.coroutines.MainScope
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RandomDogScreenKtTest {

    private lateinit var viewModel: RandomDogViewModel

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        viewModel = mockk(relaxed = true)
        composeTestRule.setContent {
            RandomDogScreen(viewModel)
        }
    }

    @Test
    fun randomDogScreen() {
        // arrange

        // act

        // assert
        composeTestRule.onNodeWithText("RANDOM!").assertExists()
    }

    @After
    fun tearDown() {
    }



    @Test
    fun randomDogView() {
    }
}
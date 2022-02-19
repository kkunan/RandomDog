package com.kkunan.randomdogandroid.features.randomimage.presentation.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RandomDogComposeView() {
    Text(text = "random dog")
}

@Preview
@Composable
fun PreviewRandomDog(){
    RandomDogComposeView()
}
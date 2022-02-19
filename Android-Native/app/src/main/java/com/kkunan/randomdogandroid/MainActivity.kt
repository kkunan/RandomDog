package com.kkunan.randomdogandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.kkunan.randomdogandroid.common.presentation.theme.RandomDogAndroidTheme
import com.kkunan.randomdogandroid.features.randomimage.data.apis.DogCeoApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomDogAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
//    LaunchedEffect(key1 = "L1", block = {
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = DogCeoApiInterface.create().getRandomDogImage(2)
//            println("response: ${response}")
//        }
//    })
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RandomDogAndroidTheme {
        Greeting("Android")
    }
}
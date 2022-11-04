package com.kkunan.kmm.randomdog.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.kkunan.kmm.randomdog.Greeting
import com.kkunan.kmm.randomdog.android.theme.RandomDogAndroidTheme
import com.kkunan.kmm.randomdog.data.api.HttpClientWrapper
import com.kkunan.kmm.randomdog.data.api.HttpClientWrapperImpl
import com.kkunan.kmm.randomdog.data.api.httpClient
import com.kkunan.kmm.randomdog.data.datasources.DogImageNetworkDatasourceImpl
import com.kkunan.kmm.randomdog.data.datasources.NetworkStatusDatasourceImpl
import com.kkunan.kmm.randomdog.data.repositories.DogImageRepositoryImpl
import com.kkunan.kmm.randomdog.domain.usecases.RandomDogImage
import com.kkunan.kmm.randomdog.presentation.viewstatehandler.RandomDogViewStateHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val stateHandler by inject<RandomDogViewStateHandler>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomDogAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RandomDogScreen(
                        stateHandler
                    )
                }
            }
        }
    }
}

@Composable
fun RandomDogScreen (randomDogViewStateHandler: RandomDogViewStateHandler) {
    val currentState = randomDogViewStateHandler.uiState
    val state = remember { mutableStateOf(currentState) }
    val painter = rememberAsyncImagePainter(state.value.dogImageUrl)
    Surface {
        RandomDogView(painter, {
            CoroutineScope(Dispatchers.Main).launch {
                randomDogViewStateHandler.randomNextImage {
                    state.value = it
                }
            }
        })
    }
}

@Composable
fun RandomDogView(painter: Painter, onRandomClick: () -> Unit,
                  modifier: Modifier = Modifier) {
    Surface(Modifier.background(MaterialTheme.colors.surface)) {
        Image(painter = painterResource(id = R.drawable.dog_icon_foreground),
            contentDescription = "", Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )

        Column(
            modifier
                .verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter, "", modifier =
                Modifier
                    .fillMaxWidth(fraction = 0.6f)
                    .aspectRatio(1f)
                    .background(color = MaterialTheme.colors.surface)
                    .clip(RoundedCornerShape(16.dp))
                    .border(4.dp, MaterialTheme.colors.primary, RoundedCornerShape(16.dp))
                    .shadow(2.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { onRandomClick() },
                Modifier.fillMaxWidth(fraction = 0.6f),
                shape = RoundedCornerShape(50)) {
                Text("RANDOM!", style = TextStyle(fontSize = 28.sp,
                    fontWeight = FontWeight.Bold), modifier = Modifier.padding(vertical = 16.dp))
            }
        }
    }

}

@Preview
@Composable
fun PreviewRandomDog() {
    RandomDogAndroidTheme {
        RandomDogView(
            painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier.fillMaxSize(), onRandomClick = {

            })
    }
}
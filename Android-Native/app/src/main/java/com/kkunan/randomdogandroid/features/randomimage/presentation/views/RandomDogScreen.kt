package com.kkunan.randomdogandroid.features.randomimage.presentation.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.kkunan.randomdogandroid.R
import com.kkunan.randomdogandroid.common.presentation.theme.RandomDogAndroidTheme
import com.kkunan.randomdogandroid.features.randomimage.presentation.viewmodels.RandomDogViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun RandomDogScreen (randomDogViewModel: RandomDogViewModel = viewModel()) {
    val dogImage by randomDogViewModel.dogImageUrl.observeAsState(emptyList())
    val painter = rememberImagePainter(data = if (dogImage.isEmpty()) null else dogImage[0].url)
    Surface {
        RandomDogView(painter, {
            CoroutineScope(Dispatchers.Main).launch {
                randomDogViewModel.randomNewImage()
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
        RandomDogView(painterResource(R.drawable.ic_launcher_background),
        modifier = Modifier.fillMaxSize(), onRandomClick = {

            })
    }
}
package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}


@Composable
fun ArtSpaceApp(){
    var idx by remember {
        mutableStateOf(0)
    }
    var last_idx = 2

    when(idx){
        0 -> ArtSpaceUI(
            painter = R.drawable.ic_launcher_foreground,
            description = R.string.ic_launcher_foreground_description,
            title = R.string.ic_launcher_foreground_title,
            text = R.string.ic_launcher_foreground_text
        )
        1 -> ArtSpaceUI(
            painter = R.drawable.ic_launcher_background,
            description = R.string.ic_launcher_background_description,
            title = R.string.ic_launcher_background_title,
            text = R.string.ic_launcher_background_text
        )
        2 -> ArtSpaceUI(
            painter = R.drawable.lemon_drink,
            description = R.string.lemon_drink_description,
            title = R.string.lemon_drink_title,
            text = R.string.lemon_drink_text
        )
        else -> {
            if (idx < 0){
                idx = last_idx
            }
            else{
                idx = 0
            }
        }
    }

    ActionButtonRow(
        onClickPrev = { idx-- },
        onClickNext = { idx++ },
    )

}

@Composable
fun ArtSpaceUI(
    painter: Int,
    description: Int,
    title: Int,
    text: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.padding(32.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = painter),
            contentDescription = stringResource(id = description),
            modifier = Modifier
                .shadow(elevation = 4.dp, shape = RectangleShape)
                .padding(20.dp)
        )

        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            Text(
                text = stringResource(id = title),
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(id = text),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }


    }
}

@Composable
fun ActionButtonRow(
    onClickPrev : () -> Unit,
    onClickNext : () -> Unit,
    modifier: Modifier = Modifier
    ){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier.fillMaxSize(),
        ) {
        Button(
            onClick = onClickPrev,
            modifier = Modifier.padding(25.dp)
        ) {
            Text(text = stringResource(id = R.string.button_previous))
        }

        Button(
            onClick = onClickNext,
            modifier = Modifier.padding(25.dp)
        ) {
            Text(text = stringResource(id = R.string.button_next))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
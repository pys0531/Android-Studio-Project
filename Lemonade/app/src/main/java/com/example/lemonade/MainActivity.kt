package com.example.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade_App()
                }
            }
        }
    }
}

@Composable
fun Lemonade_App() {
    var current_step by remember {
        mutableStateOf(1);
    }
    var squeezeCount by remember {
        mutableStateOf(0)
    }
    when(current_step){
        1 -> LemonTextAndImage(
            text = R.string.lemon_tree,
            painter = R.drawable.lemon_tree,
            description = "lemon_tree",
            onImageClick = {
                current_step = 2
                squeezeCount = (2 .. 4).random()
            }
        )

        2 -> LemonTextAndImage(
            text =  R.string.lemon_squeeze,
            painter = R.drawable.lemon_squeeze,
            description = "lemon_squeeze",
            onImageClick = {
                squeezeCount--
                if (squeezeCount == 0){
                    current_step = 3
                }
            }
        )

        3 -> LemonTextAndImage(
            text =  R.string.lemon_drink,
            painter = R.drawable.lemon_drink,
            description = "lemon_drink",
            onImageClick = {
                current_step = 4
            }
        )

        4 -> LemonTextAndImage(
            text = R.string.lemon_restart,
            painter = R.drawable.lemon_restart,
            description = "lemon_restart",
            onImageClick = {
                current_step = 1
            }
        )

    }

}

@Composable
fun LemonTextAndImage(
    text : Int,
    painter: Int,
    description: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = text), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = painter),
            contentDescription = description,
            Modifier.clickable(onClick = onImageClick))
    }
}




@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        Lemonade_App()
    }
}
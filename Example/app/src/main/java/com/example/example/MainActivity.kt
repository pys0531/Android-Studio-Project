package com.example.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.example.ui.theme.ExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    my_show(stringResource(R.string.happy_birthday_to_you), stringResource(R.string.signature_text))
                }
            }
        }
    }
}


@Composable
fun my_text_show(name: String, from: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$name!", color = Color.Blue, fontSize = 30.sp, modifier = Modifier.padding(top = 16.dp))
        Text(
            text = "$from", color = Color.Black, fontSize = 24.sp, modifier = Modifier.padding(top = 16.dp, end = 16.dp).align(alignment = Alignment.End))
    }
}


@Composable
fun my_image_show()
{
    val image = painterResource(R.drawable.androidparty)
    Image(painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun my_show(name: String, from: String)
{
    Box{
        my_image_show()
        my_text_show(name, from)
    }

}

@Preview(showBackground = false)
@Composable
fun mytestPreview() {
    ExampleTheme {
        my_show(stringResource(R.string.happy_birthday_to_you), stringResource(R.string.signature_text))
    }
}
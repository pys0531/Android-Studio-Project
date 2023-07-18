package com.example.businesscard

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.businesscard.ui.theme.BusinessCardTheme
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    Column(Modifier.background(color = Color.DarkGray)) {
        BusinessCardTitle(Modifier.weight(5f))
        BusinessCardInfo(Modifier.weight(2f))
    }
}

@Composable
fun BusinessCardTitle(modifier: Modifier)
{
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.android_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(150.dp)

        )
        Text(
            text = stringResource(R.string.name),
            color = Color.White,
            fontSize = 45.sp,
            fontWeight = FontWeight.W300
        )
        Text(
            text = stringResource(R.string.introduce),
            color = colorResource(R.color.android),
            fontSize = 18.sp
        )
    }
}

@Composable
fun BusinessCardInfo(modifier: Modifier)
{
    Column(
        modifier = modifier.fillMaxSize().padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Divider(
            color = Color.Black,
            modifier = Modifier.fillMaxWidth().height(1.dp)
        )
        RowInfo(
            painterResource(R.drawable.phone_number),
            stringResource(R.string.phone_number)
        )

        Divider(
            color = Color.Black,
            modifier = Modifier.fillMaxWidth().height(1.dp)
        )
        RowInfo(
            painterResource(R.drawable.link),
            stringResource(R.string.link)
        )

        Divider(
            color = Color.Black,
            modifier = Modifier.fillMaxWidth().height(1.dp)
        )
        RowInfo(
            painterResource(R.drawable.email),
            stringResource(R.string.email)
        )
    }
}

@Composable
fun RowInfo(painter: Painter,
            info: String)
{
    Row (
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = info,
            modifier = Modifier.weight(3f),
            textAlign = TextAlign.Justify,
            color = Color.White,
            fontSize = 18.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCardApp()
    }
}
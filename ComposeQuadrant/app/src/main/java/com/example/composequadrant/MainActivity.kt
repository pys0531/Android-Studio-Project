package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeQuadrantApp()
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrantApp()
{
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            ComposeQuadrantBox(
                title = stringResource(R.string.first_title),
                contents = stringResource(R.string.first_contents),
                modifier = Modifier.weight(1f)
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(color = Color.Green)
            )

            ComposeQuadrantBox(
                title = stringResource(R.string.second_title),
                contents = stringResource(R.string.second_contents),
                modifier = Modifier.weight(1f)
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(color = Color.Yellow)
            )
        }

        Row(Modifier.weight(1f)) {
            ComposeQuadrantBox(
                title = stringResource(R.string.third_title),
                contents = stringResource(R.string.third_contents),
                modifier = Modifier.weight(1f)
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(color = Color.Cyan)
            )

            ComposeQuadrantBox(
                title = stringResource(R.string.fourth_title),
                contents = stringResource(R.string.fourth_contents),
                modifier = Modifier.weight(1f)
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(color = Color.LightGray)
            )
        }
    }
}

@Composable
private fun ComposeQuadrantBox(title: String,
                       contents: String,
                       modifier: Modifier = Modifier
)
{
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = contents,
            textAlign = TextAlign.Justify
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ComposeQuadrantPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrantApp()
    }
}
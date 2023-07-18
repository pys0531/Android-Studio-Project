package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycity.data.DataSource
import com.example.mycity.model.MenuItem

@Composable
fun SelectItemScreen(
    options: List<MenuItem>,
    onClick: (MenuItem) -> Unit
){

    LazyColumn {
        items(options) {option ->
            val onClick = {
                onClick(option)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .clickable(onClick = onClick),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = option.image),
                    contentDescription = stringResource(id = option.name),
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = stringResource(id = option.name),
                    modifier = Modifier.weight(3f),
                    fontSize = 25.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectItemSceenPreview(){
    SelectItemScreen(
        options = DataSource.city,
        onClick = {}
    )
}
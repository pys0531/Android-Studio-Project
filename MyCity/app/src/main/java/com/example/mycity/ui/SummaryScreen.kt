package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycity.R
import com.example.mycity.data.DataSource
import com.example.mycity.model.MenuItem
import com.example.mycity.model.PlaceUiState

@Composable
fun SummaryScreen(
    option: PlaceUiState,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = option.recommend.image),
            contentDescription = stringResource(id = option.recommend.name)
        )
        Text(
            text = stringResource(id = option.recommend.descript)
        )
        Button(
            onClick = onClick
        ) {
            Text(stringResource(R.string.home).uppercase())
        }
    }
}

@Preview
@Composable
fun SummaryScreenPreview(){
    SummaryScreen(
        option = PlaceUiState(
            city = MenuItem.City(
                DataSource.defaultCity.image,
                DataSource.defaultCity.name,
                DataSource.defaultCity.descript),
            recommend = MenuItem.Recommend(
                DataSource.defaultRecommend.image,
                DataSource.defaultRecommend.name,
                DataSource.defaultRecommend.descript)
        ),
        onClick = {}
    )
}
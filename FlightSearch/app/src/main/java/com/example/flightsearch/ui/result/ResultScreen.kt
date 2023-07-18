package com.example.flightsearch.ui.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.ui.FavoriteListUiState
import com.example.flightsearch.ui.FlightTopBar
import com.example.flightsearch.ui.home.HomeDestination
import com.example.flightsearch.ui.home.HomeViewModel
import com.example.flightsearch.ui.navigation.NavigationDestination

object ResultDestination : NavigationDestination {
    override val route = "result"
    override val titleRes = R.string.app_name
    const val resultIdArg = "resultId"
    val routeWithArgs = "$route/{$resultIdArg}"
}

@Composable
fun ResultScreen(
    navigateUp: () -> Unit,
    viewModel: ResultViewModel = viewModel(factory = ResultViewModel.Factory),
    modifier: Modifier = Modifier
){
    val uiState = viewModel.uiState
    val favoriteUiState = viewModel.favoriteUiState.collectAsState()

    Scaffold(
        topBar = {
            FlightTopBar(
                title = stringResource(ResultDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateUp,
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        ResultBody(
            uiState = uiState,
            favoriteUiState = favoriteUiState.value,
            favoriteAction = viewModel::insertFavorite,
            modifier = modifier.padding(innerPadding)
        )
    }

}

@Composable
fun ResultBody(
    uiState: ResultUiState,
    favoriteUiState: FavoriteListUiState,
    favoriteAction: (String, String) -> Unit,
    modifier: Modifier = Modifier
){
    AirportList(
        uiState = uiState,
        favoriteUiState = favoriteUiState,
        favoriteAction = favoriteAction,
        modifier = modifier
    )
}

@Composable
private fun AirportList(
    uiState: ResultUiState,
    favoriteUiState: FavoriteListUiState,
    favoriteAction: (String, String) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier.padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = uiState.destinationCode, key = { it.id }) { item ->
            val isFavorite = favoriteUiState.itemList.find { f ->
                f.departureCode == uiState.departureCode.iataCode &&
                        f.destinationCode == item.iataCode }
            AirportCard(
                uiState = uiState,
                destination = item,
                favoriteAction = favoriteAction,
                isFavorite = isFavorite != null,
                modifier = modifier
            )
            Divider()
        }
    }
}

@Composable
fun AirportCard(
    uiState: ResultUiState,
    destination: Airport,
    favoriteAction: (String, String) -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = uiState.departureCode.iataCode,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = uiState.departureCode.name,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(text = "->")

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = destination.iataCode,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = destination.name,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        IconButton(
            onClick = { favoriteAction(uiState.departureCode.iataCode, destination.iataCode) },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                tint = if (isFavorite) Color.Red else Color.LightGray,
                contentDescription = stringResource(id = R.string.favorite_button)
            )

        }
    }
}
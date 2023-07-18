package com.example.flightsearch.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Query
import androidx.room.util.query
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.ui.FlightTopBar
import com.example.flightsearch.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen(
    navigateToIdUpdate: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
    modifier: Modifier = Modifier
){
    val favoriteUiState by viewModel.favoriteUiState.collectAsState()
    val uiState = viewModel.homeUiState

    Scaffold(
        topBar = {
            FlightTopBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                modifier = Modifier
            )
        }
    ) {innerPadding ->
        HomeBody(
            favoriteList = favoriteUiState.itemList,
            uiState = uiState,
            onItemValueChange = viewModel::updateQuery,
            onAirportClick = navigateToIdUpdate,
            onFavoriteClick = viewModel::isFavorite,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeBody(
    favoriteList: List<Favorite>,
    uiState: HomeUiState,
    onItemValueChange: (HomeUiState) -> Unit,
    onAirportClick: (Int) -> Unit,
    onFavoriteClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = uiState.searchQuery,
            onValueChange = { onItemValueChange(uiState.copy(searchQuery = it)) },
            placeholder = { Text(text = "Search here") },
            modifier = Modifier.fillMaxWidth()
        )

        if (uiState.searchQuery.isEmpty()){
            if (favoriteList.isEmpty()){
                Text(
                    text = stringResource(R.string.no_item_favorite),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            else{
                FavoriteList(
                    itemList = favoriteList,
                    onItemClick = onFavoriteClick,
                )
            }
        }
        else{
            AirportList(
                itemList = uiState.airportListUiState,
                onItemClick = onAirportClick
            )
        }

    }
}

@Composable
private fun AirportList(
    itemList: List<Airport>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = itemList, key = { it.id }) { item ->
            Row(modifier = modifier.clickable { onItemClick(item.id) }) {
                Spacer(modifier = Modifier.width(24.dp))

                Text(text = item.iataCode)

                Spacer(modifier = Modifier.width(24.dp))

                Text(
                    text = item.name,
                    maxLines = 1
                )
            }

            Divider()
        }
    }
}

@Composable
private fun FavoriteList(
    itemList: List<Favorite>,
    onItemClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = itemList, key = { it.id }) { item ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    item.departureCode + " -> " + item.destinationCode,
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Center
                )
                IconButton(onClick = { onItemClick(item.departureCode, item.destinationCode) }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        tint = Color.Red,
                        contentDescription = stringResource(id = R.string.favorite_button)
                    )
                }
            }
            Divider()
        }
    }
}
package com.example.mycity

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycity.ui.theme.MyCityTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.data.DataSource
import com.example.mycity.ui.PlaceViewModel
import com.example.mycity.ui.SelectItemScreen
import com.example.mycity.ui.SummaryScreen


enum class MyCity(@StringRes val title: Int){
    Start(title = R.string.app_name),
    Recommend(title = R.string.recommend),
    Summary(title = R.string.summary)
}

@Composable
fun MyCityApp(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCity.valueOf(
        backStackEntry?.destination?.route ?: MyCity.Start.name
    )

    val viewModel: PlaceViewModel = viewModel()

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) {innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        
        var option by rememberSaveable{ mutableStateOf("") }

        NavHost(
            navController = navController,
            startDestination = MyCity.Start.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = MyCity.Start.name){
                SelectItemScreen(
                    options = DataSource.city,
                    onClick = {
                        viewModel.updateCity(it)
                        navController.navigate(MyCity.Recommend.name)
                    }
                )
            }

            composable(route = MyCity.Recommend.name){
                option = stringResource(id = uiState.city.name)

                SelectItemScreen(
                    options = when(option){
                        stringResource(R.string.namguro) -> DataSource.namguro_recommend
                        stringResource(R.string.hongdae) -> DataSource.hongdae_recommend
                        stringResource(R.string.sillim) -> DataSource.sillim_recommend
                        else -> DataSource.namguro_recommend
                    },
                    onClick = {
                        viewModel.updateRecommend(it)
                        navController.navigate(MyCity.Summary.name)
                    }
                )
            }

            composable(route = MyCity.Summary.name){
                SummaryScreen(
                    option = uiState,
                    onClick = {
                        cancelAndStartButton(navController, viewModel)
                    }
                )
            }
        }
    }
}

private fun cancelAndStartButton(
    navController: NavController,
    viewModel: PlaceViewModel
){
    viewModel.resetRecommend()
    navController.popBackStack(MyCity.Start.name, false)
}

@Composable
fun AppBar(
    currentScreen: MyCity,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        },
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyCityTheme {
        MyCityApp()
    }
}
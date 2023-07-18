package com.example.amphibians.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.amphibians.ui.Screen.AmphibiansViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.amphibians.R
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.amphibians.ui.Screen.HomeScreen
import com.example.amphibians.ui.Screen.InfoScreen

enum class Amphibians(){
    Home,
    Description
}

@Composable
fun AmphibiansApp(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: Amphibians.Home.name


    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        },
        modifier = modifier
    ){innerPadding ->
        val amphibiansViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)

        NavHost(
            navController = navController,
            startDestination = Amphibians.Home.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = Amphibians.Home.name){
                HomeScreen(
                    startButton = {
                        navController.navigate(route = Amphibians.Description.name)
                    }
                )
            }

            composable(route = Amphibians.Description.name){
                InfoScreen(
                    amphibiansUiState = amphibiansViewModel.amphibiansUiState,
                    retryAction = {
                        cancelAndStartButton(navController, amphibiansViewModel)
                    }
                )
            }
        }

    }

}

private fun cancelAndStartButton(
    navController: NavController,
    viewModel: AmphibiansViewModel
){
    viewModel.getAmphibiansPhotos()
    navController.popBackStack(Amphibians.Home.name, false)
}

@Composable
fun AppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(text = currentScreen) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            }
        },
        modifier = modifier
    )
}
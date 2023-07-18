package com.example.bookshelf.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.R
import com.example.bookshelf.ui.Screen.BooksViewModel
import com.example.bookshelf.ui.Screen.InfoScreen
import com.example.bookshelf.ui.Screen.SearchScreen

enum class AppDestinations(val title: String){
    Search(title = "Search"),
    Info(title = "Result")
}

@Composable
fun BookselfApp(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val BackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppDestinations.valueOf(
        BackStackEntry?.destination?.route ?: AppDestinations.Search.name
    )

    Scaffold(
        topBar = {
            TopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) {innerPadding ->
        val booksViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)

        NavHost(
            navController = navController,
            startDestination = AppDestinations.Search.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = AppDestinations.Search.name){
                SearchScreen(
                    booksViewModel = booksViewModel,
                    retryAction = {
                        cancelAndStartButton(navController, booksViewModel)
                    }
                )
            }

            composable(route = AppDestinations.Info.name){
                InfoScreen()
            }
        }
    }
}


private fun cancelAndStartButton(
    navController: NavController,
    viewModel: BooksViewModel
){
    viewModel.getBookInfos()
    //navController.popBackStack(AppDestinations.Search.name, false)
}

@Composable
fun TopBar(
    currentScreen: AppDestinations,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
){
    TopAppBar(
        title = {
            Text(text = currentScreen.title)
        },
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, 
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            }
        }
    )
}
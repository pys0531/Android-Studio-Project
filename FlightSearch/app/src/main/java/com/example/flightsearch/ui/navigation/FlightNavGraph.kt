package com.example.flightsearch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.flightsearch.ui.home.HomeDestination
import com.example.flightsearch.ui.home.HomeScreen
import com.example.flightsearch.ui.result.ResultDestination
import com.example.flightsearch.ui.result.ResultScreen

@Composable
fun FlightNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route
    ){
        composable(route = HomeDestination.route){
            HomeScreen(
                navigateToIdUpdate = {
                    navController.navigate("${ResultDestination.route}/${it}")
                }
            )
        }

        composable(
            route = ResultDestination.routeWithArgs,
            arguments = listOf(navArgument(ResultDestination.resultIdArg) {
                type = NavType.IntType
            })
        ){
            ResultScreen(
                navigateUp = { navController.navigateUp() },
            )
        }
    }
}
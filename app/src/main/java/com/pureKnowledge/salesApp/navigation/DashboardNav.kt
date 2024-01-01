package com.pureKnowledge.salesApp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pureKnowledge.salesApp.util.Constants.HOME_GRAPH_ROUTE


@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.dashboardRoute(
    navController: NavHostController
) {

    navigation(
        startDestination = Screen.Dashboard.route,
        route = HOME_GRAPH_ROUTE
    ){
        composable(route = Screen.Dashboard.route) {

        }

    }
}
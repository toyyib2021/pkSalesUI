package com.pureKnowledge.salesApp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pureKnowledge.salesApp.util.Constants.AUTH_GRAPH_ROUTE
import com.pureKnowledge.salesApp.util.Constants.ROOT_GRAPH_ROUTE

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AUTH_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE

    ) {
        authRoute(navController)
        dashboardRoute(navController)
        priceRoute(navController)
        customerRoute(navController)
        stockRecordRoute(navController)
    }
}


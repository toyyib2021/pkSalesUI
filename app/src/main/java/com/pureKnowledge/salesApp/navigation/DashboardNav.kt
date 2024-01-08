package com.pureKnowledge.salesApp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.pureKnowledge.salesApp.screen.customer.AddCustomerScreen
import com.pureKnowledge.salesApp.screen.customer.CustomerScreen
import com.pureKnowledge.salesApp.screen.home.Dashboard
import com.pureKnowledge.salesApp.screen.home.DashboardScreen
import com.pureKnowledge.salesApp.screen.login.LoginScreen
import com.pureKnowledge.salesApp.screen.order.PlaceOrderScreen
import com.pureKnowledge.salesApp.screen.priceList.AddProductPriceScreen
import com.pureKnowledge.salesApp.screen.priceList.ProductPriceScreen
import com.pureKnowledge.salesApp.screen.searchCustomer.SearchCustomerScreen
import com.pureKnowledge.salesApp.screen.signUp.SignUpScreen
import com.pureKnowledge.salesApp.screen.viewModel.TestUIScreen
import com.pureKnowledge.salesApp.util.Constants
import com.pureKnowledge.salesApp.util.Constants.AUTH_GRAPH_ROUTE
import com.pureKnowledge.salesApp.util.Constants.HOME_GRAPH_ROUTE
import io.realm.kotlin.mongodb.App


@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.dashboardRoute(
    navController: NavHostController
) {

    navigation(
        startDestination = Screen.Dashboard.route,
        route = HOME_GRAPH_ROUTE

    ){

        composable(route = Screen.Dashboard.route) {
//            TestUIScreen()
            DashboardScreen(
                onOrderClick = { /*TODO*/ },
                onCustomerClick = {navController.navigate(Screen.Customer.route) },
                onProductClick = { /*TODO*/ },
                onRevenueClick = { /*TODO*/ },
                onDebitClick = { /*TODO*/ },
                onCustomerSearchClick = {navController.navigate(Screen.CustomerSearch.route)},
                onPriceClick = {navController.navigate(Screen.Product.route)}
            )
        }

        // Customer ************************************************* ////////////////////////////////////
        composable(route = Screen.Customer.route) {
            CustomerScreen(
                addCustomer = {navController.navigate(Screen.AddCustomer.route)},
                onBackCLick = {navController.navigate(Screen.Dashboard.route)},
                onHistroyClick = {},
                onPlaceOrderClick = {navController.navigate(Screen.CustomerHistory.customerHistoryID(it))}
            )
        }

        composable(route = Screen.AddCustomer.route) {
            AddCustomerScreen (
                onBackClick ={navController.navigate(Screen.Customer.route)},
                onSubmitClick = {navController.navigate(Screen.Customer.route)}
            )
        }

        composable(route = Screen.CustomerSearch.route) {
            SearchCustomerScreen(
                onBackCLick = { navController.navigate(Screen.Dashboard.route) },
                addCustomer = {navController.navigate(Screen.AddCustomer.route)},
                onHistroyClick = {},
                onPlaceOrderClick = {navController.navigate(Screen.CustomerHistory.customerHistoryID(it))}
            )
        }


        // Order ************************************************* ////////////////////////////////////
        composable(
            route= Screen.CustomerHistory.route,
            arguments = listOf(
                navArgument(NAVKEY){
                    type= NavType.StringType })
        ){ key ->
            val customerId = key.arguments?.getString(NAVKEY) ?: ""
            PlaceOrderScreen(customerId = customerId)
        }



        // Product ************************************************* ////////////////////////////////////

        composable(route = Screen.Product.route) {
            ProductPriceScreen(
                onBackCLick = {navController.navigate(Screen.Dashboard.route)},
                onAddClick = {navController.navigate(Screen.AddProduct.route)}
            )
        }

        composable(route = Screen.AddProduct.route) {
            AddProductPriceScreen(
                onBackClick = {navController.navigate(Screen.Product.route)},
                onSubmitClick = {navController.navigate(Screen.Product.route)}
            )
        }


    }
}
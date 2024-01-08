package com.pureKnowledge.salesApp.navigation

const val NAVKEY = "sale_nav_id"

sealed class Screen(val route: String) {

    // Auth Screens
    object Authentication : Screen(route = "authentication_screen")
    object SignIn : Screen(route = "sign_in_screen")
    object Register : Screen(route = "register_screen")
    object AccessPin : Screen(route = "access_pin_screen")
    object ForgetAccessPin : Screen(route = "forget_access_pin_screen")
    object ChangeAccessPin : Screen(route = "change_access_pin_screen")
    object CreateAccessPin : Screen(route = "create_access_pin_screen")

    object Dashboard : Screen(route = "dashboard_screen")


    // Customers Screens
    object Customer : Screen(route = "customer_screen")
    object AddCustomer : Screen(route = "add_customer_screen")
    object CustomerSearch : Screen(route = "customer_search_screen")
    object CustomerHistory : Screen(route = "customer_history_screen/{$NAVKEY}"){
        fun customerHistoryID(key: String): String{
            return "customer_history_screen/$key"
        }
    }


    // Product Screens
    object Product : Screen(route = "product_screen")
    object AddProduct : Screen(route = "add_product_screen")



}

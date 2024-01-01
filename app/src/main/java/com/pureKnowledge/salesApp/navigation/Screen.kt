package com.pureKnowledge.salesApp.navigation

sealed class Screen(val route: String) {

    // Auth Screens
    object Authentication : Screen(route = "authentication_screen")
    object SignUp : Screen(route = "sign_up_screen")
    object SignIn : Screen(route = "sign_in_screen")
    object AccessPin : Screen(route = "access_pin_screen")
    object ForgetAccessPin : Screen(route = "forget_access_pin_screen")
    object ChangeAccessPin : Screen(route = "change_access_pin_screen")
    object Activation : Screen(route = "activation_screen")
    object Dashboard : Screen(route = "dashboard_screen")


    // Home Screens

}

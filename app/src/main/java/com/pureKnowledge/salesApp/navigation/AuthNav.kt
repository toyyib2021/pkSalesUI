package com.pureKnowledge.salesApp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pureKnowledge.salesApp.screen.accessPin.AccessPinScreen
import com.pureKnowledge.salesApp.screen.accessPin.ChangeAccessPinScreen
import com.pureKnowledge.salesApp.screen.accessPin.ForgetAccessPinScreen
import com.pureKnowledge.salesApp.screen.activation.ActivationScreen
import com.pureKnowledge.salesApp.screen.login.LoginScreen
import com.pureKnowledge.salesApp.screen.signUp.SignUpScreen
import com.pureKnowledge.salesApp.util.Constants.AUTH_GRAPH_ROUTE


fun NavGraphBuilder.authRoute(
    navController: NavHostController
) {

    navigation(
        startDestination = Screen.AccessPin.route,
        route = AUTH_GRAPH_ROUTE
    ){
        composable(route = Screen.SignUp.route) {
            SignUpScreen(
                onSignUpClick = { navController.navigate(Screen.Activation.route) },
                onBackCLick = { navController.navigate(Screen.SignIn.route) },
                onSignInClick = { navController.navigate(Screen.SignIn.route) })
        }

        composable(route = Screen.SignIn.route) {
            LoginScreen(
                onLoginClick = { navController.navigate(Screen.Activation.route) },
                onSignUpClick = { navController.navigate(Screen.SignUp.route) })
        }

        composable(route = Screen.Activation.route) {
            ActivationScreen(
                onFreeTrailClick = { navController.navigate(Screen.AccessPin.route) },
                onActivateClick = { navController.navigate(Screen.AccessPin.route) },
                onBackCLick = { navController.navigate(Screen.SignIn.route) }
            )
        }


        composable(route = Screen.AccessPin.route) {
            AccessPinScreen(
                onContinueClick = { navController.navigate(Screen.Dashboard.route) },
                onBackCLick = { navController.navigate(Screen.SignIn.route) },
                onContinueClickPin = { navController.navigate(Screen.Dashboard.route)},
                onChangePinClickPin = { navController.navigate(Screen.ChangeAccessPin.route) },
                onForgetPinClick = { navController.navigate(Screen.ForgetAccessPin.route) },
            )
        }

        composable(route = Screen.ForgetAccessPin.route) {
            ForgetAccessPinScreen(
                onBackCLick = {navController.navigate(Screen.AccessPin.route) },
                onContinueClick = {navController.navigate(Screen.Dashboard.route)})

        }

        composable(route = Screen.ChangeAccessPin.route) {
            ChangeAccessPinScreen(
                onBackCLick = {navController.navigate(Screen.AccessPin.route) },
                onContinueClick = {navController.navigate(Screen.Dashboard.route)})
        }

    }


}
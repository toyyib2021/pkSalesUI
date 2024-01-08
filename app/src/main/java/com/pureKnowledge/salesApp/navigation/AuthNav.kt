package com.pureKnowledge.salesApp.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pureKnowledge.salesApp.screen.accessPin.AccessPinScreen
import com.pureKnowledge.salesApp.screen.accessPin.ChangeAccessPinScreen
import com.pureKnowledge.salesApp.screen.accessPin.CreateAccessPin
import com.pureKnowledge.salesApp.screen.accessPin.CreateAccessPinScreen
import com.pureKnowledge.salesApp.screen.accessPin.ForgetAccessPinScreen
import com.pureKnowledge.salesApp.screen.activation.ActivationScreen
import com.pureKnowledge.salesApp.screen.login.LoginScreen
import com.pureKnowledge.salesApp.screen.signUp.SignUpScreen
import com.pureKnowledge.salesApp.util.Constants
import com.pureKnowledge.salesApp.util.Constants.AUTH_GRAPH_ROUTE
import com.pureKnowledge.salesApp.util.Constants.HOME_GRAPH_ROUTE
import io.realm.kotlin.mongodb.App


fun NavGraphBuilder.authRoute(
    navController: NavHostController
) {
    val app: App = App.create(Constants.APP_ID)
    val user = app.currentUser


    navigation(
        startDestination = if (user != null) Screen.AccessPin.route else Screen.SignIn.route,
        route =  AUTH_GRAPH_ROUTE

    ) {

        composable(route = Screen.SignIn.route) {
            LoginScreen(
                onLoginClick = { navController.navigate(Screen.AccessPin.route) },
                onSignUpClick = { navController.navigate(Screen.Register.route) })
        }

        composable(route = Screen.Register.route) {
            SignUpScreen(
                onSignUpClick = {Screen.Dashboard.route},
                onBackCLick = { navController.navigate(Screen.SignIn.route) },
                navController = navController,
                onSignInClick = {navController.navigate(Screen.SignIn.route)}
            )
        }

        composable(route = Screen.AccessPin.route) {
            AccessPinScreen(
                onContinueClick = { navController.navigate(Screen.Dashboard.route) },
                onBackCLick = { navController.navigate(Screen.SignIn.route) },
                onContinueClickPin = { navController.navigate(Screen.Dashboard.route)},
                onChangePinClickPin = { navController.navigate(Screen.ChangeAccessPin.route) },
                onForgetPinClick = { navController.navigate(Screen.ForgetAccessPin.route) },
                createPinScreen = { navController.navigate(Screen.CreateAccessPin.route)}
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

        composable(route = Screen.CreateAccessPin.route) {
            CreateAccessPinScreen(
                onBackCLick = { navController.navigate(Screen.AccessPin.route) },
                onContinueClick = { navController.navigate(Screen.Dashboard.route) }
            )

        }

    }

}
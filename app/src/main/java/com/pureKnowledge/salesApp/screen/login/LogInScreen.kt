package com.pureKnowledge.salesApp.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun LoginScreen(
    onLoginClick:()->Unit,
    onSignUpClick:()->Unit,
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    LoginUI(
        onLoginClick = onLoginClick,
        onEmailChange = {email = it},
        onPasswordChange = {password = it},
        email = email,
        emailLabel = "Email",
        password = password,
        passwordLabel ="Password",
        onSignUpClick = onSignUpClick
    )
}